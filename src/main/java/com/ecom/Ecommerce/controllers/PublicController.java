package com.ecom.Ecommerce.controllers;

import com.ecom.Ecommerce.payloads.CustomerDto;
import com.ecom.Ecommerce.payloads.LoginDto;
import com.ecom.Ecommerce.payloads.MerchantDto;
import com.ecom.Ecommerce.services.CustomUserDetailsService;
import com.ecom.Ecommerce.services.CustomerService;
import com.ecom.Ecommerce.services.JwtService;
import com.ecom.Ecommerce.services.MerchantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
@Slf4j
public class PublicController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService userDetailsService;

    @Autowired
    JwtService jwtService;

    @Autowired
    CustomerService customerService;

    @Autowired
    MerchantService merchantService;

    @PostMapping("/createCustomerAcc")
    public ResponseEntity<CustomerDto> createCustomerAccount(@RequestBody CustomerDto customerDto){
        CustomerDto customerDto1=customerService.createAcc(customerDto);
        return new  ResponseEntity<>(customerDto1,HttpStatus.OK);
    }

    @PostMapping("/createMerchantAcc")
    public ResponseEntity<?> createMerchant(@RequestBody MerchantDto merchantDto){
        return new ResponseEntity<>(merchantService.createAcc(merchantDto),HttpStatus.OK);
    }

        @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
            log.info("Authentication successful for user: {}", loginDto.getEmail());
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getEmail());
            String jwt = jwtService.generateToken(userDetails);
            log.info("Generated JWT Token: {}", jwt);
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteAcc/{email}")
    public ResponseEntity<String> deleteCustomerAcc(@PathVariable String email){
        return new ResponseEntity<>(customerService.deleteAccount(email),HttpStatus.OK);
    }
}
