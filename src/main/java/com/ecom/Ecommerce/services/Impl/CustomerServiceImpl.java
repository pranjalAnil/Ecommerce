package com.ecom.Ecommerce.services.Impl;
import com.ecom.Ecommerce.constants.Constant;
import com.ecom.Ecommerce.entities.OrderedProd;
import com.ecom.Ecommerce.entities.Products;
import com.ecom.Ecommerce.payloads.CustomerDto;
import com.ecom.Ecommerce.entities.Customer;
import com.ecom.Ecommerce.payloads.OrderDto;
import com.ecom.Ecommerce.payloads.OrderPlaced;
import com.ecom.Ecommerce.payloads.PreviousOrders;
import com.ecom.Ecommerce.repo.CustomerRepo;
import com.ecom.Ecommerce.repo.OrderedProdRepo;
import com.ecom.Ecommerce.repo.ProductRepo;
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
        Customer customer= customerRepo.findByEmail(email).orElseThrow();
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
        customerRepo.delete(customerRepo.findByEmail(email).orElseThrow());
        return "Account Deleted";
    }

    @Override
    public OrderPlaced orderProd(int customerId,int prodID, OrderDto orderedDto) {
        OrderedProd orderedProd=new OrderedProd();
        Customer customer=customerRepo.findById(customerId).orElseThrow();
        Products products=productRepo.findById(prodID).orElseThrow();
        if(products.getNumOfProducts()!=0) {
            BeanUtils.copyProperties(orderedDto, orderedProd);
            orderedProd.setProductId(products.getProdId());
            orderedProd.setCustomerId(customerId);
            orderedProd.setStatus(Constant.order);
            orderedProdRepo.save(orderedProd);
            products.setNumOfProducts(products.getNumOfProducts() - 1);
            productRepo.save(products);
            OrderPlaced orderPlaced = new OrderPlaced();
            orderPlaced.setProducts(products);
            orderPlaced.setOrderId(orderedProd.getOrderId());
            orderPlaced.setStr("Order Placed");
            return orderPlaced;
        }
        else {
            throw new RuntimeException();
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
             orderedProd1.setProducts(productRepo.findById(orderedProd.getProductId()).orElseThrow());
             previousOrders.add(orderedProd1);
         }
         return previousOrders;


    }


}
