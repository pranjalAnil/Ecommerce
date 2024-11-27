package com.ecom.Ecommerce.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int merchantId;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String mobile;

    private String password;
    private String address;
    private String merchantName;
    private String companyName;

    @OneToMany(mappedBy = "merchant")
    @JsonManagedReference
    private List<Products> productsList = new ArrayList<>();

}
