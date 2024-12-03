package com.ecom.Ecommerce.controllers;

import com.ecom.Ecommerce.services.DeliveryBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveryBoy")
public class DeliveryBoy {
    @Autowired
    DeliveryBoyService deliveryBoyService;

    @GetMapping("/{deliveryBoyId}/getShipments")
    public ResponseEntity<?> getShipments(@PathVariable int deliveryBoyId){
        return new ResponseEntity<>(deliveryBoyService.getAllShipments(deliveryBoyId),HttpStatus.OK);
    }

    @PutMapping("/{deliveryBoyId}/shipment/{shipmentId}/deliver")
    public ResponseEntity<?> deliverOrder(@PathVariable int deliveryBoyId,@PathVariable int shipmentId){
        return new ResponseEntity<>(deliveryBoyService.successfulDelivery(shipmentId,deliveryBoyId),HttpStatus.OK);
    }
    @PutMapping("/{deliveryBoyId}/shipment/{shipmentId}/cancelOrder")
    public ResponseEntity<?> cancelOrder(@PathVariable int deliveryBoyId,@PathVariable int shipmentId){
        return new ResponseEntity<>(deliveryBoyService.orderCancelation(shipmentId,deliveryBoyId),HttpStatus.OK);
    }


}
