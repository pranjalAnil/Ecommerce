package com.ecom.Ecommerce.services.Impl;
import com.ecom.Ecommerce.Exception.EmailAlreadyExists;
import com.ecom.Ecommerce.Exception.OrderedProdMoreThanNumOfProd;
import com.ecom.Ecommerce.Exception.ResourceNotFoundException;
import com.ecom.Ecommerce.constants.Constant;
import com.ecom.Ecommerce.entities.*;
import com.ecom.Ecommerce.payloads.*;
import com.ecom.Ecommerce.repo.*;
import com.ecom.Ecommerce.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    OrderedProdRepo orderedProdRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    DeliveryBoyRepo deliveryBoyRepo;

    @Autowired
    ShipmentRepo shipmentRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    MerchantRepo merchantRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    @Override
    public CustomerDto createAcc(CustomerDto customerDto) {
        String email=customerDto.getEmail();
        List<DeliveryBoy> deliveryBoyList=deliveryBoyRepo.findAll();

        for(DeliveryBoy deliveryBoy:deliveryBoyList){
            if (deliveryBoy.getEmail().equals(email)){
                throw new EmailAlreadyExists("email","emailID",deliveryBoy.getEmail());
            }
        }
        List<Merchant> merchantList=merchantRepo.findAll();
        for (Merchant merchant:merchantList){
            if(merchant.getEmail().equals(email)){
                throw new EmailAlreadyExists("email","emailID",email);
            }
        }

        Customer customer =new Customer();
        BeanUtils.copyProperties(customerDto,customer);
        customer.setPassword(encoder.encode(customer.getPassword()));
        customer.setRoles(Arrays.asList("ROLE_CUSTOMER"));
        customerRepo.save(customer);
        BeanUtils.copyProperties(customer,customerDto);
        return customerDto;
    }

    @Override
    public CustomerDto updateAcc(CustomerDto customerDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();

        Customer customer= customerRepo.findByEmail(email).orElseThrow(
                ()->new ResourceNotFoundException("customer","email " +email,0)
        );

        List<DeliveryBoy> deliveryBoyList=deliveryBoyRepo.findAll();
        for(DeliveryBoy deliveryBoy:deliveryBoyList){
            if (deliveryBoy.getEmail().equals(customerDto.getEmail())){
                throw new EmailAlreadyExists("email","emailID",deliveryBoy.getEmail());
            }
        }
        List<Merchant> merchantList=merchantRepo.findAll();
        for (Merchant merchant:merchantList){
            if(merchant.getEmail().equals(customerDto.getEmail())){
                throw new EmailAlreadyExists("email","emailID",email);
            }
        }
        customer.setCustomerName(customerDto.getCustomerName());
        if (!Objects.equals(customerDto.getEmail(), email) || customerDto.getEmail() != null) {
            customer.setEmail(customerDto.getEmail());
        }
        customer.setMobile(customerDto.getMobile());
        customer.setAddress(customerDto.getAddress());
        customer.setPassword(customer.getPassword());
        customerRepo.save(customer);
        BeanUtils.copyProperties(customer, customerDto);
        return customerDto;
    }

    @Override
    public String deleteAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        customerRepo.delete(customerRepo.findByEmail(email).orElseThrow(
                ()->new ResourceNotFoundException("customer","email " +email,0)
        ));
        return "Account Deleted";
    }

    @Override
    public OrderPlaced orderProd(int prodID, OrderDto orderedDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        Customer customer = customerRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("customer","customerId "+email,0));

        OrderedProd orderedProd=new OrderedProd();
        Products products=productRepo.findById(prodID).orElseThrow(
                ()->new ResourceNotFoundException("product","productId",prodID)
        );
        if(products.getNumOfProducts()!=0) {

            if(orderedDto.getQuantity()<=products.getNumOfProducts()) {
                BeanUtils.copyProperties(orderedDto, orderedProd);
                orderedProd.setProductId(products.getProdId());
                orderedProd.setCustomerId(customer.getCustomerId());
                orderedProd.setStatus(Constant.order);
                orderedProdRepo.save(orderedProd);
                products.setNumOfProducts(products.getNumOfProducts() - orderedDto.getQuantity());
                productRepo.save(products);

                Shipment shipment=new Shipment();
                shipment.setOrderId(orderedProd.getOrderId());
                shipment.setProdId(prodID);
                shipment.setStatus(Constant.order);
                shipment.setCustomerId(customer.getCustomerId());
                shipmentRepo.save(shipment);

                OrderPlaced orderPlaced = new OrderPlaced();
                orderPlaced.setProducts(products);
                orderPlaced.setOrderId(orderedProd.getOrderId());
                orderPlaced.setStr("Order Placed");

                return orderPlaced;
            }
            else {
                throw new OrderedProdMoreThanNumOfProd(orderedDto.getQuantity(),products.getNumOfProducts());
            }
        }
        else {
            throw new OrderedProdMoreThanNumOfProd(orderedDto.getQuantity());
        }
    }

    @Override
    public Customer findByEmail(String email) {
        return customerRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("email","emailId "+email,0));
    }

    @Override
    public List<PreviousOrders> myOrders(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        Customer customer = customerRepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("customer","customerId "+email,0));

        List<OrderedProd> orderedProdList= orderedProdRepo.findByCustomerId(customer.getCustomerId());
         List<PreviousOrders> previousOrders =new ArrayList<>();
         for(OrderedProd orderedProd:orderedProdList){
             PreviousOrders orderedProd1=new PreviousOrders();
             orderedProd1.setQuantity(orderedProd.getQuantity());
             orderedProd1.setOrderId(orderedProd.getOrderId());
             orderedProd1.setStatus(orderedProd.getStatus());
             orderedProd1.setProducts(productRepo.findById(orderedProd.getProductId()).orElseThrow(
                     ()->new ResourceNotFoundException("product","productId",orderedProd.getProductId())
             ));
             previousOrders.add(orderedProd1);
         }
         return previousOrders;


    }






}
