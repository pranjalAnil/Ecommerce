package com.ecom.Ecommerce.payloads;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class LoginDto {
    @Email
    private String email;
    private String password;
}
