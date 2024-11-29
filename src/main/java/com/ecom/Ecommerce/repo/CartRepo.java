package com.ecom.Ecommerce.repo;

import com.ecom.Ecommerce.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart, Integer> {
    public List<Cart> findByCustomerId(Integer customerId);
}
