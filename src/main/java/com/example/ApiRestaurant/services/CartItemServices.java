package com.example.ApiRestaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiRestaurant.entities.CartItem;
import com.example.ApiRestaurant.entities.Product;
import com.example.ApiRestaurant.entities.DTO.CartDTO;
import com.example.ApiRestaurant.exception.ProductNotFoundException;
import com.example.ApiRestaurant.repositories.CartItemRepository;
import com.example.ApiRestaurant.repositories.ProductRepository;

@Service
public class CartItemServices{
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    CartItemRepository cartItemRepository;

    
    public CartItem createItemforCart(CartDTO cartdto) {
       Product existingProduct =  productRepository.findById(cartdto.getProductId()).orElseThrow( () -> new ProductNotFoundException("PRODUCTO NO ENCONTRADO"));
       CartItem newItem = new CartItem();
       
       newItem.setCartItemQuantity(1);
       newItem.setCartProduct(existingProduct);
       return cartItemRepository.save(newItem);
    }
}
