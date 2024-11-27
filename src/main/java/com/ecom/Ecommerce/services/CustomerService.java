package com.ecom.Ecommerce.services;

import com.ecom.Ecommerce.payloads.CustomerDto;

public interface CustomerService {
    public CustomerDto createAcc(CustomerDto customerDto);
    public CustomerDto updateAcc(CustomerDto customerDto,String email);
    public String deleteAccount(String email);
}
