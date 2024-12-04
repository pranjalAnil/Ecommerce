package com.ecom.Ecommerce.controllers;


import com.ecom.Ecommerce.services.DeliveryBoyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/deliveryBoy")
@PreAuthorize("hasRole('ROLE_DELIVERY')")
public class DeliveryBoy {
    @Autowired
    DeliveryBoyService deliveryBoyService;

    @GetMapping("/getShipments")
    public ResponseEntity<?> getShipments(){
        return new ResponseEntity<>(deliveryBoyService.getAllShipments(),HttpStatus.OK);
    }

    @PutMapping("/shipment/{shipmentId}/deliver")
    public ResponseEntity<?> deliverOrder(@PathVariable int shipmentId){
        return new ResponseEntity<>(deliveryBoyService.successfulDelivery(shipmentId),HttpStatus.OK);
    }

    @PutMapping("/{deliveryBoyId}/shipment/{shipmentId}/cancelOrder")
    public ResponseEntity<?> cancelOrder(@PathVariable int shipmentId){
        return new ResponseEntity<>(deliveryBoyService.orderCancelation(shipmentId),HttpStatus.OK);
    }


}
