package com.ecom.Ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipId;
    private String status;
    private int prodId;
    private int orderId;
    private int CustomerId;
    @OneToMany
    private List<Products> products = new ArrayList<>();
}
