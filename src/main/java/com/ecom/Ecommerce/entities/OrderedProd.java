package com.ecom.Ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;



@Entity
@Data
public class OrderedProd{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;
    private int quantity;

//    @ManyToOne
//    private Customer customer;
//    @OneToOne
//    private Products products;
    private int customerId;
    private int productId;
    private String status;
}
