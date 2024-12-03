package com.ecom.Ecommerce.payloads;

import com.ecom.Ecommerce.entities.Products;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDto {
    private int cartId;
    private int customerId;
    private ProductsDto productsDto;
}
