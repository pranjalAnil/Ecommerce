package com.ecom.Ecommerce.payloads;
import lombok.Data;

@Data
public class CategoryDto {
    private int categoryID;
    private String categoryTitle;
    private String categoryDescription;
}
