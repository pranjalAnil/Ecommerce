package com.ecom.Ecommerce.services.Impl;
import com.ecom.Ecommerce.Exception.OrderedProdMoreThanNumOfProd;
import com.ecom.Ecommerce.Exception.ResourceNotFoundException;
import com.ecom.Ecommerce.constants.Constant;
import com.ecom.Ecommerce.entities.*;
import com.ecom.Ecommerce.payloads.*;
import com.ecom.Ecommerce.repo.*;
import com.ecom.Ecommerce.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    OrderedProdRepo orderedProdRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ShipmentRepo shipmentRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public CustomerDto createAcc(CustomerDto customerDto) {
        Customer customer =new Customer();
        BeanUtils.copyProperties(customerDto,customer);
        customerRepo.save(customer);
        BeanUtils.copyProperties(customer,customerDto);
        return customerDto;
    }

    @Override
    public CustomerDto updateAcc(CustomerDto customerDto,String email) {
        Customer customer= customerRepo.findByEmail(email).orElseThrow(
                ()->new ResourceNotFoundException("customer","email " +email,0)
        );
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
    public String deleteAccount(String email) {
        customerRepo.delete(customerRepo.findByEmail(email).orElseThrow(
                ()->new ResourceNotFoundException("customer","email " +email,0)
        ));
        return "Account Deleted";
    }

    @Override
    public OrderPlaced orderProd(int customerId, int prodID, OrderDto orderedDto) {

        OrderedProd orderedProd=new OrderedProd();
        Customer customer=customerRepo.findById(customerId).orElseThrow(
                ()->new ResourceNotFoundException("customer","customerId",customerId)
        );
        Products products=productRepo.findById(prodID).orElseThrow(
                ()->new ResourceNotFoundException("product","productId",prodID)
        );
        if(products.getNumOfProducts()!=0) {

            if(orderedDto.getQuantity()<=products.getNumOfProducts()) {
                BeanUtils.copyProperties(orderedDto, orderedProd);
                orderedProd.setProductId(products.getProdId());
                orderedProd.setCustomerId(customerId);
                orderedProd.setStatus(Constant.order);
                orderedProdRepo.save(orderedProd);
                products.setNumOfProducts(products.getNumOfProducts() - orderedDto.getQuantity());
                productRepo.save(products);

                Shipment shipment=new Shipment();
                shipment.setOrderId(orderedProd.getOrderId());
                shipment.setProdId(prodID);
                shipment.setStatus(Constant.order);
                shipment.setCustomerId(customerId);
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
    public List<PreviousOrders> myOrders(int customerId){
        List<OrderedProd> orderedProdList= orderedProdRepo.findByCustomerId(customerId);
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
