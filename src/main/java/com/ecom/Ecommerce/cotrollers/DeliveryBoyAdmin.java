package com.ecom.Ecommerce.cotrollers;
import com.ecom.Ecommerce.payloads.DeliveryBoyDto;
import com.ecom.Ecommerce.services.DeliveryBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-deliveryBoy")
public class DeliveryBoyAdmin {
    @Autowired
    DeliveryBoyService deliveryBoyService;
    @PostMapping("/add")
    public ResponseEntity<?> addAcc(@RequestBody DeliveryBoyDto deliveryBoyDto){
        return new ResponseEntity<>(deliveryBoyService.addDeliverBoy(deliveryBoyDto),HttpStatus.OK);

    }
    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteAcc(@PathVariable String email){
        return new ResponseEntity<>(deliveryBoyService.deleteDeliverBoy(email),HttpStatus.OK);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(deliveryBoyService.getAllDeliverBoy(),HttpStatus.OK);
    }
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email){
        return new ResponseEntity<>(deliveryBoyService.getDeliveryBoyByEmail(email),HttpStatus.OK);
    }


}
