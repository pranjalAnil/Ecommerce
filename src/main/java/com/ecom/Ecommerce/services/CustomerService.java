package com.ecom.Ecommerce.services;

import com.ecom.Ecommerce.entities.OrderedProd;
import com.ecom.Ecommerce.payloads.CustomerDto;
import com.ecom.Ecommerce.payloads.OrderDto;
import com.ecom.Ecommerce.payloads.OrderPlaced;
import com.ecom.Ecommerce.payloads.PreviousOrders;

import java.util.List;

public interface CustomerService {
    public List<PreviousOrders> myOrders(int customerId);
    public CustomerDto createAcc(CustomerDto customerDto);
    public CustomerDto updateAcc(CustomerDto customerDto,String email);
    public String deleteAccount(String email);
    public OrderPlaced orderProd(int customerId, int ProdID, OrderDto orderDto);
}
