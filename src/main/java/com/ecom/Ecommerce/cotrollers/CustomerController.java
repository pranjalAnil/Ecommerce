package com.ecom.Ecommerce.cotrollers;

import com.ecom.Ecommerce.entities.Cart;
import com.ecom.Ecommerce.payloads.CartDto;
import com.ecom.Ecommerce.payloads.CustomerDto;
import com.ecom.Ecommerce.payloads.ProductsDto;
import com.ecom.Ecommerce.services.CartService;
import com.ecom.Ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CartService cartService;

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

    @PostMapping("/addCart/{customerId}/{prodId}")
    public ResponseEntity<CartDto> addToCart(@PathVariable int customerId, @PathVariable int prodId) {
        CartDto cartDto1 = cartService.addToCart(customerId, prodId);
        return new ResponseEntity<>(cartDto1, HttpStatus.OK);
    }

    @GetMapping("/getCart/{customerId}")
    public ResponseEntity<?> getCart(@PathVariable int customerId) {
       List<CartDto> cartDto = cartService.getCart(customerId);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

   /* @PostMapping("/order")
    public ResponseEntity<?> orderProduct(@PathVariable int prodId) {

    }*/
}
