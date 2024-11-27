package com.ecom.Ecommerce.payloads;
import lombok.Data;

@Data
public class CustomerDto {

    private int customerId;
    private String email;
    private String mobile;
    private String password;
    private String address;
    private String customerName;

}
