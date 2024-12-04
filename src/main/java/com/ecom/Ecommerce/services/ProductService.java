package com.ecom.Ecommerce.services;
import com.ecom.Ecommerce.payloads.ProductsDto;

import java.util.List;

public interface ProductService {
    public ProductsDto addProducts(ProductsDto productsDto,int categoryId);
    public ProductsDto updateProduct(ProductsDto productsDto,int prodId);
    public List<ProductsDto> getAllProducts();
    public String deleteProducts(int prodId);
    public ProductsDto orderProduct(int prodId);

}
