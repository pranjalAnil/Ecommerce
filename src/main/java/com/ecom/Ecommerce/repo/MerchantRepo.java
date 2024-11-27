package com.ecom.Ecommerce.repo;

import com.ecom.Ecommerce.entities.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MerchantRepo extends JpaRepository<Merchant,Integer> {
    Optional<Merchant> findByEmail(String email);
}
