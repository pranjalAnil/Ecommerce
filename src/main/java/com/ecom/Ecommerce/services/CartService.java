package com.ecom.Ecommerce.services;

import com.ecom.Ecommerce.entities.Cart;
import com.ecom.Ecommerce.payloads.CartDto;

import java.util.List;


public interface CartService {
    public CartDto addToCart(int customerId, int prodId);
    public List<CartDto> getAllCart();
    public List<CartDto> getCart(int customerId);
    public CartDto removeFromCart(int prodId);
    public CartDto orderProduct(int proId);
}
