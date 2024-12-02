package com.ecom.Ecommerce.payloads;

import com.ecom.Ecommerce.entities.Customer;
import lombok.Data;

@Data
public class ShipmentDto {
    private int shipId;
    private String status;
    private int prodId;
    private CustomerDtoShipment customerDtoShipment;
}
