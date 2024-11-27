package com.ecom.Ecommerce.repo;

import com.ecom.Ecommerce.entities.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Products,Integer> {
}
