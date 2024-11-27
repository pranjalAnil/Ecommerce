package com.ecom.Ecommerce.services.Impl;

import com.ecom.Ecommerce.payloads.MerchantDto;
import com.ecom.Ecommerce.entities.Merchant;
import com.ecom.Ecommerce.repo.MerchantRepo;
import com.ecom.Ecommerce.services.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    MerchantRepo merchantRepo;
    @Override
    public MerchantDto createAcc(MerchantDto merchantDto) {
        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDto,merchant);
        merchantRepo.save(merchant);
        BeanUtils.copyProperties(merchant,merchantDto);
        return merchantDto;
    }

    @Override
    public MerchantDto updateAcc(MerchantDto merchantDto, String email) {
        Merchant merchant=merchantRepo.findByEmail(email).orElseThrow();
        merchant.setMerchantName(merchantDto.getMerchantName());
        merchant.setAddress(merchantDto.getAddress());
        merchant.setMobile(merchantDto.getMobile());
        merchant.setPassword(merchant.getPassword());
        merchant.setCompanyName(merchantDto.getCompanyName());
        if (!Objects.equals(merchantDto.getEmail(), email) || merchantDto.getEmail() != null) {
            merchant.setEmail(merchantDto.getEmail());
        }
        merchantRepo.save(merchant);
        BeanUtils.copyProperties(merchant,merchantDto);
        return merchantDto;
    }

    @Override
    public String deleteAccount(String email) {
        merchantRepo.delete(merchantRepo.findByEmail(email).orElseThrow());
        return "User Deleted";
    }
}