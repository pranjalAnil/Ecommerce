package com.ecom.Ecommerce.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String mobile;
    private String password;
    private String address;
    private String customerName;

    @ManyToMany(mappedBy = "customers")
    @JsonManagedReference
    private List<Products> productsList = new ArrayList<>();




}
