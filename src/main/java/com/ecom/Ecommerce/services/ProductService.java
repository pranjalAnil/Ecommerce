package com.ecom.Ecommerce.services;
import com.ecom.Ecommerce.payloads.ProductsDto;

public interface ProductService {
    public ProductsDto addProducts();
    public ProductsDto updateProduct();
    public ProductsDto getAllProducts();
    public ProductsDto deleteProducts();
}
