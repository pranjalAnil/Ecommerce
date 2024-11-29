package com.ecom.Ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int shipId;
    private String status;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    private List<Products> products = new ArrayList<>();
}
