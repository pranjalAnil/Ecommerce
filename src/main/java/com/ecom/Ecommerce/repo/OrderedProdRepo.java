package com.ecom.Ecommerce.repo;

import com.ecom.Ecommerce.entities.DeliveryBoy;
import com.ecom.Ecommerce.entities.OrderedProd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderedProdRepo extends JpaRepository<OrderedProd,Integer> {
    List<OrderedProd> findByCustomerId(int customerId);
}
