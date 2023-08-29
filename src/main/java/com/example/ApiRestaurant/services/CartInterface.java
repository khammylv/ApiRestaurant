package com.example.ApiRestaurant.services;

import com.example.ApiRestaurant.entities.Cart;
import com.example.ApiRestaurant.entities.DTO.CartDTO;
import com.example.ApiRestaurant.exception.ProductNotFoundException;

public interface CartInterface {
    public Cart addProductToCart(CartDTO cart, Long id) throws ProductNotFoundException;
    
}
