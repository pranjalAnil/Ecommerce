package com.ecom.Ecommerce.payloads;

import com.ecom.Ecommerce.entities.Products;
import lombok.Data;

@Data
public class PreviousOrders {
    private int orderId;
    private int quantity;
    private String status;
    private Products products;
}