package com.ecom.Ecommerce.cotrollers;

import com.ecom.Ecommerce.payloads.CustomerDto;
import com.ecom.Ecommerce.payloads.OrderDto;
import com.ecom.Ecommerce.payloads.OrderPlaced;
import com.ecom.Ecommerce.payloads.ProductsDto;
import com.ecom.Ecommerce.services.CustomerService;
import com.ecom.Ecommerce.services.ProductService;
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
    ProductService productService;

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

    @GetMapping("/all-products")
    public ResponseEntity<?> getAllProducts() {
        List<ProductsDto> all = productService.getAllProducts();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{customerId}/orderProd/{prodId}")
    public ResponseEntity<OrderPlaced> orderPro(@RequestBody OrderDto orderDto, @PathVariable int prodId,@PathVariable int customerId){
        return new ResponseEntity<>(customerService.orderProd(customerId,prodId,orderDto),HttpStatus.OK);
    }

    @GetMapping("/{customerId}/myOrders")
    public ResponseEntity<?> getProdList(@PathVariable int customerId){
        return new ResponseEntity<>(customerService.myOrders(customerId),HttpStatus.OK);
    }

}
