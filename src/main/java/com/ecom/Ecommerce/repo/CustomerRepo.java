package com.ecom.Ecommerce.repo;

import com.ecom.Ecommerce.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepo extends JpaRepository<Customer,Integer> {
    Optional<Customer> findByEmail(String email);

}
