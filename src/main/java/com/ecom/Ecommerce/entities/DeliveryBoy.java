package com.ecom.Ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class DeliveryBoy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deliveryBoyId;
    @Column(unique = true)
    private String mobile;
    @Column(unique = true)
    private String name;
    private String email;
}
