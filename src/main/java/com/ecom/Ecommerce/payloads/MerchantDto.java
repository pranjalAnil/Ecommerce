package com.ecom.Ecommerce.payloads;
import lombok.Data;

@Data
public class MerchantDto {
    private int id;

    private String email;
    private String mobile;
    private String password;
    private String address;
    private String merchantName;
    private String companyName;

}
