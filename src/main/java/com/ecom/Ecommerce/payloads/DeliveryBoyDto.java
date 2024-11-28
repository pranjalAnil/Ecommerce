package com.ecom.Ecommerce.payloads;

import lombok.Data;

@Data
public class DeliveryBoyDto {
    private int deliveryBoyId;
    private String mobile;
    private String name;

    private String email;
}
