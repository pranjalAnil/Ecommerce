package com.ecom.Ecommerce.cotrollers;

import com.ecom.Ecommerce.payloads.CustomerDto;
import com.ecom.Ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/createAcc")
    public ResponseEntity<CustomerDto> createCustomerAccount(@RequestBody CustomerDto customerDto){
        CustomerDto customerDto1=customerService.createAcc(customerDto);
        return new  ResponseEntity<>(customerDto1,HttpStatus.OK);
    }

    @PutMapping("/updateAcc/{email}")
    public ResponseEntity<CustomerDto> updateCustomerAccount(@RequestBody CustomerDto customerDto,@PathVariable String email){
        CustomerDto customerDto1=customerService.updateAcc(customerDto,email);
        return new ResponseEntity<>(customerDto1,HttpStatus.OK);
    }

    @DeleteMapping("/deleteAcc/{email}")
    public ResponseEntity<String> deleteCustomerAcc(@PathVariable String email){
        return new ResponseEntity<>(customerService.deleteAccount(email),HttpStatus.OK);
    }


}
