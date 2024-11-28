package com.ecom.Ecommerce.services.Impl;
import com.ecom.Ecommerce.entities.Products;
import com.ecom.Ecommerce.payloads.ProductsDto;
import com.ecom.Ecommerce.repo.ProductRepo;
import com.ecom.Ecommerce.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Override
    public ProductsDto addProducts(ProductsDto productsDto) {
        Products products = new Products();
        BeanUtils.copyProperties(productsDto, products);
        productRepo.save(products);
        return productsDto;
    }

    @Override
    public ProductsDto updateProduct(ProductsDto productsDto, int prodId) {
        Products products = productRepo.findById(prodId).orElseThrow();
        products.setProdName(productsDto.getProdName());
        products.setAbout(productsDto.getAbout());
        products.setImageName(productsDto.getImageName());
        products.setNumOfProducts(productsDto.getNumOfProducts());
        productRepo.save(products);
        BeanUtils.copyProperties(products, productsDto);
        return productsDto;
    }

    @Override
    public List<ProductsDto> getAllProducts() {
        List<Products> list = productRepo.findAll();
        List<ProductsDto> list1 = new ArrayList<>();
        for (Products products : list){
            ProductsDto productsDto = new ProductsDto();
            BeanUtils.copyProperties(products, productsDto);
            list1.add(productsDto);
        }
        System.out.println(list1);
         return list1;

    }

    @Override
    public String deleteProducts(int prodId) {
        productRepo.deleteById(prodId);
        return "Product deleted";
    }

    @Override
    public ProductsDto orderProduct(int prodId) {
       Products products = productRepo.findById(prodId).orElseThrow();
      // products.setOrderId
        return null;
    }
}
