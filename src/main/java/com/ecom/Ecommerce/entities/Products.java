package com.ecom.Ecommerce.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;
import com.ecom.Ecommerce.entities.Customer;

@Entity
@Data
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private int prodId;
    private String prodName;
    private String about;
    private String imageName;
    private int numOfProducts;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "merchantId")
    private Merchant merchant;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "customer_products",
            joinColumns = @JoinColumn(name = "prodId"),
            inverseJoinColumns = @JoinColumn(name = "customerId")
    )
    private List<Customer> customers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}