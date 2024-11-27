package com.ecom.Ecommerce.payloads;

import lombok.Data;

@Data
public class ProductsDto {
    private int prodId;
    private String prodName;
    private String about;
    private int numOfProducts;
    private String imageName;
}