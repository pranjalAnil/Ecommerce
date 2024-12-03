package com.ecom.Ecommerce.payloads;

import com.ecom.Ecommerce.entities.Products;
import lombok.Data;

@Data
public class OrderPlaced {
    private Products products;
    private int orderId;
    private String str;
}
