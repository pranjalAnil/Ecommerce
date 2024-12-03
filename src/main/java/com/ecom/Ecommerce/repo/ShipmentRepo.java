package com.ecom.Ecommerce.repo;

import com.ecom.Ecommerce.entities.Customer;
import com.ecom.Ecommerce.entities.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepo extends JpaRepository<Shipment,Integer>{
}
