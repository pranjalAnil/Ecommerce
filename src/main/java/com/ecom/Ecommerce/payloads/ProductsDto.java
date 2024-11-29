package com.ecom.Ecommerce.payloads;


import com.ecom.Ecommerce.entities.Category;
import com.ecom.Ecommerce.entities.Merchant;
import lombok.Data;

@Data
public class ProductsDto {
    private int prodId;
    private String prodName;
    private String about;
    private int numOfProducts;
    private String imageName;
    private Merchant merchant;
    private Category category;

}