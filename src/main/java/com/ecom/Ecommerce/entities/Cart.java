package com.ecom.Ecommerce.entities;

import com.ecom.Ecommerce.payloads.ProductsDto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cartId;
    private int customerId;
    private int productId;

//    @OneToOne
//    private Products products;
}
