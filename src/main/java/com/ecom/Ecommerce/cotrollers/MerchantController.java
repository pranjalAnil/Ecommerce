package com.ecom.Ecommerce.cotrollers;

import com.ecom.Ecommerce.payloads.MerchantDto;
import com.ecom.Ecommerce.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Autowired
    MerchantService merchantService;

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


}
