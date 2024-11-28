package com.ecom.Ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;
    private String title;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Products> products = new ArrayList<>();
}
