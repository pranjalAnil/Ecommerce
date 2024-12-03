package com.ecom.Ecommerce.payloads;

import lombok.Data;

@Data
public class CustomerDtoShipment {
    private int customerId;
    private String email;
    private String mobile;
    private String address;
    private String customerName;
}
