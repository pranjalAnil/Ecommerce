package com.ecom.Ecommerce.payloads;

import com.ecom.Ecommerce.entities.Products;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShipmentDto {
    private int shipId;
    private String status;
    private List<Products> products = new ArrayList<>();
}
