package com.ecom.Ecommerce.repo;

import com.ecom.Ecommerce.entities.Customer;
import com.ecom.Ecommerce.entities.DeliveryBoy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryBoyRepo extends JpaRepository<DeliveryBoy,Integer> {
    Optional<DeliveryBoy> findByEmail(String email);
}
