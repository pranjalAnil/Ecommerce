package com.ecom.Ecommerce.cotrollers;

import com.ecom.Ecommerce.payloads.MerchantDto;
import com.ecom.Ecommerce.payloads.ProductsDto;
import com.ecom.Ecommerce.services.MerchantService;
import com.ecom.Ecommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    MerchantService merchantService;

    @Autowired
    ProductService productService;

    @PostMapping("/createAcc")
    public ResponseEntity<?> createMerchant(@RequestBody MerchantDto merchantDto){
        return new ResponseEntity<>(merchantService.createAcc(merchantDto),HttpStatus.OK);
    }

    @PutMapping("/updateAcc/{email}")
    public ResponseEntity<?> updateAcc(@RequestBody MerchantDto  merchantDto, @PathVariable String email){
        return new ResponseEntity<>(merchantService.updateAcc(merchantDto, email),HttpStatus.OK);

    }

    @DeleteMapping("/deleteAcc/{email}")
    public ResponseEntity<?> deleteAcc(@PathVariable String email){
        return new ResponseEntity<>(merchantService.deleteAccount(email),HttpStatus.OK);
    }

    @PostMapping("/{merchantId}/add-product")
    public ResponseEntity<ProductsDto> addProducts(@RequestBody ProductsDto productsDto,@PathVariable int merchantId) {
        ProductsDto productsDto1 = productService.addProducts(productsDto,merchantId);
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
