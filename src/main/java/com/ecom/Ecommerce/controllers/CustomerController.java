package com.ecom.Ecommerce.controllers;
import com.ecom.Ecommerce.entities.Customer;
import com.ecom.Ecommerce.payloads.CartDto;
import com.ecom.Ecommerce.payloads.CustomerDto;
import com.ecom.Ecommerce.payloads.OrderDto;
import com.ecom.Ecommerce.payloads.OrderPlaced;
import com.ecom.Ecommerce.payloads.ProductsDto;
import com.ecom.Ecommerce.services.CartService;
import com.ecom.Ecommerce.services.CustomerService;
import com.ecom.Ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;

    @PutMapping("/updateAcc")
    public ResponseEntity<CustomerDto> updateCustomerAccount(@RequestBody CustomerDto customerDto){
        CustomerDto customerDto1=customerService.updateAcc(customerDto);
        return new ResponseEntity<>(customerDto1,HttpStatus.OK);
    }

    @DeleteMapping("/deleteAcc")
    public ResponseEntity<String> deleteCustomerAcc(){
        return new ResponseEntity<>(customerService.deleteAccount(),HttpStatus.OK);
    }

    @GetMapping("/all-products")
    public ResponseEntity<?> getAllProducts() {
        List<ProductsDto> all = productService.getAllProducts();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/orderProd/{prodId}")
    public ResponseEntity<OrderPlaced> orderPro(@RequestBody OrderDto orderDto, @PathVariable int prodId){
        return new ResponseEntity<>(customerService.orderProd(prodId,orderDto),HttpStatus.OK);
    }

    @GetMapping("/{customerId}/myOrders")
    public ResponseEntity<?> getProdList(){
        return new ResponseEntity<>(customerService.myOrders(),HttpStatus.OK);
    }

    @PostMapping("/addCart/{prodId}")
    public ResponseEntity<CartDto> addToCart(@PathVariable int prodId) {
        CartDto cartDto1 = cartService.addToCart(prodId);
        return new ResponseEntity<>(cartDto1, HttpStatus.OK);
    }

    @GetMapping("/getCart")
    public ResponseEntity<?> getCart() {
       List<CartDto> cartDto = cartService.getCart();
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/removeCart/{cartId}")
    public ResponseEntity<?> removeFromCart(@PathVariable int cartId) {
        return new ResponseEntity<>(cartService.removeFromCart(cartId), HttpStatus.OK);
    }

}
