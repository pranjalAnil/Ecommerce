package com.ecom.Ecommerce.controllers;

import com.ecom.Ecommerce.payloads.MerchantDto;
import com.ecom.Ecommerce.payloads.ProductsDto;
import com.ecom.Ecommerce.services.MerchantService;
import com.ecom.Ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/merchant")
@PreAuthorize("hasRole('ROLE_MERCHANT')")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductService productService;

    @PutMapping("/updateAcc/{email}")
    public ResponseEntity<?> updateAcc(@RequestBody MerchantDto  merchantDto){
        return new ResponseEntity<>(merchantService.updateAcc(merchantDto),HttpStatus.OK);
    }

    @DeleteMapping("/deleteAcc/{email}")
    public ResponseEntity<?> deleteAcc(){
        return new ResponseEntity<>(merchantService.deleteAccount(),HttpStatus.OK);
    }

    @PostMapping("/category/{categoryId}/add-product")
    public ResponseEntity<ProductsDto> addProducts(@RequestBody ProductsDto productsDto,@PathVariable int categoryId) {
        ProductsDto productsDto1 = productService.addProducts(productsDto,categoryId);
        return new ResponseEntity<>(productsDto1, HttpStatus.CREATED);
    }

    @GetMapping("/all-products")
    public ResponseEntity<?> getAllProducts() {
        List<ProductsDto> all = productService.getAllProducts();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update-product/{prodId}")
    public ResponseEntity<ProductsDto> updateProduct(@RequestBody ProductsDto productsDto, @PathVariable int prodId ){
        return new ResponseEntity<>(productService.updateProduct(productsDto,prodId), HttpStatus.OK);

    }

    @DeleteMapping("/deleteProd/{prodId}")
    public ResponseEntity<?> deleteProduct(@PathVariable int prodId) {
        return new ResponseEntity<>(productService.deleteProducts(prodId), HttpStatus.OK);
    }

}
