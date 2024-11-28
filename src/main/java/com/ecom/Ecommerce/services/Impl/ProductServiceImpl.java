package com.ecom.Ecommerce.services.Impl;
import com.ecom.Ecommerce.payloads.ProductsDto;
import com.ecom.Ecommerce.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public ProductsDto addProducts(ProductsDto productsDto) {
        return null;
    }

    @Override
    public ProductsDto updateProduct(ProductsDto productsDto, int prodId) {
        return null;
    }

    @Override
    public List<ProductsDto> getAllProducts() {
        return null;
    }

    @Override
    public ProductsDto deleteProducts(int productId) {
        return null;
    }
}
