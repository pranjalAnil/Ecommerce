package com.ecom.Ecommerce.services;

import com.ecom.Ecommerce.payloads.MerchantDto;

public interface MerchantService {
    public MerchantDto createAcc(MerchantDto merchantDto);
    public MerchantDto updateAcc(MerchantDto customerDto,String email);
    public String deleteAccount(String email);
}
