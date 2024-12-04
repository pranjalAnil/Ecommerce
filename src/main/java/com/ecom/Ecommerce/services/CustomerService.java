package com.ecom.Ecommerce.services;

import com.ecom.Ecommerce.entities.Customer;
import com.ecom.Ecommerce.payloads.CustomerDto;
import com.ecom.Ecommerce.payloads.OrderDto;
import com.ecom.Ecommerce.payloads.OrderPlaced;
import com.ecom.Ecommerce.payloads.PreviousOrders;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public List<PreviousOrders> myOrders();
    public CustomerDto createAcc(CustomerDto customerDto);
    public CustomerDto updateAcc(CustomerDto customerDto);
    public String deleteAccount();
    public OrderPlaced orderProd(int ProdID, OrderDto orderDto);
    public Customer findByEmail(String email);
}
