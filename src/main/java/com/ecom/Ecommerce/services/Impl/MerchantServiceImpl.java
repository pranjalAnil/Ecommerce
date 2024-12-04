package com.ecom.Ecommerce.services.Impl;

import com.ecom.Ecommerce.Exception.ResourceNotFoundException;
import com.ecom.Ecommerce.payloads.MerchantDto;
import com.ecom.Ecommerce.entities.Merchant;
import com.ecom.Ecommerce.repo.MerchantRepo;
import com.ecom.Ecommerce.services.MerchantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service
public class MerchantServiceImpl implements MerchantService {
    @Autowired
    MerchantRepo merchantRepo;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public MerchantDto createAcc(MerchantDto merchantDto) {
        Merchant merchant=new Merchant();
        BeanUtils.copyProperties(merchantDto,merchant);
        merchant.setPassword(encoder.encode(merchant.getPassword()));
        merchant.setRoles(Arrays.asList("ROLE_MERCHANT"));
        merchantRepo.save(merchant);
        BeanUtils.copyProperties(merchant,merchantDto);
        return merchantDto;
    }

    @Override
    public MerchantDto updateAcc(MerchantDto merchantDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        Merchant merchant=merchantRepo.findByEmail(email).orElseThrow(
                ()->new ResourceNotFoundException("Merchant","email"+email,0)
        );
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
    public String deleteAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email =  authentication.getName();
        merchantRepo.delete(merchantRepo.findByEmail(email).orElseThrow(
                ()->new ResourceNotFoundException("Merchant","email"+email,0)
        ));
        return "User Deleted";
    }
}
