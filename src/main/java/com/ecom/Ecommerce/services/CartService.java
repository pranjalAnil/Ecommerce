package com.ecom.Ecommerce.services;

import com.ecom.Ecommerce.entities.Cart;
import com.ecom.Ecommerce.payloads.CartDto;

import java.util.List;


public interface CartService {
    public CartDto addToCart(int prodId);
    public List<CartDto> getAllCart();
    public List<CartDto> getCart();
    public String removeFromCart(int cartId);
    public CartDto orderProduct(int proId);
}
