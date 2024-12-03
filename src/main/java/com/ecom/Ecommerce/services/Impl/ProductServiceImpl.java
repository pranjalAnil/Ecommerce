package com.ecom.Ecommerce.services.Impl;
import com.ecom.Ecommerce.Exception.ResourceNotFoundException;
import com.ecom.Ecommerce.entities.Category;
import com.ecom.Ecommerce.entities.Merchant;
import com.ecom.Ecommerce.entities.Products;
import com.ecom.Ecommerce.payloads.ProductsDto;
import com.ecom.Ecommerce.repo.CategoryRepo;
import com.ecom.Ecommerce.repo.MerchantRepo;
import com.ecom.Ecommerce.repo.ProductRepo;
import com.ecom.Ecommerce.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    MerchantRepo merchantRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Override
    public ProductsDto addProducts(ProductsDto productsDto, int merchantID,int categoryId) {
        Products products = new Products();
        Merchant merchant= merchantRepo.findById(merchantID).orElseThrow(
                ()->new ResourceNotFoundException("merchant","merchantId",merchantID)
        );
        Category category=categoryRepo.findById(categoryId).orElseThrow(
                ()->new ResourceNotFoundException("category","categoryId",categoryId)
        );
        products.setCategory(category);
        products.setMerchant(merchant);
        BeanUtils.copyProperties(productsDto, products);
        productRepo.save(products);
        BeanUtils.copyProperties(products,productsDto);
        return productsDto;
    }

    @Override
    public ProductsDto updateProduct(ProductsDto productsDto, int prodId) {
        Products products = productRepo.findById(prodId).orElseThrow(
                ()->new ResourceNotFoundException("product","productId",prodId)
        );
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
        try {
            productRepo.deleteById(prodId);
            return "Product deleted";
        }catch (ResourceNotFoundException resourceNotFoundException){
            throw new ResourceNotFoundException("product","productId",prodId);
        }

    }
    @Override
    public ProductsDto orderProduct(int prodId) {
       Products products = productRepo.findById(prodId).orElseThrow(
               ()->new ResourceNotFoundException("product","productId",prodId)
       );
       ProductsDto productsDto=new ProductsDto();
       BeanUtils.copyProperties(products,productsDto);
        return productsDto;
    }
}
