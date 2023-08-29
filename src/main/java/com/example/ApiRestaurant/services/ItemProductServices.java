package com.example.ApiRestaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiRestaurant.entities.ItemProduct;
import com.example.ApiRestaurant.entities.Product;
import com.example.ApiRestaurant.exception.ProductNotFoundException;
import com.example.ApiRestaurant.repositories.ItemProductRepository;
import com.example.ApiRestaurant.repositories.ProductRepository;

@Service
public class ItemProductServices {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ItemProductRepository itemRepository;

    public ItemProduct addItemProduct(ItemProduct product) {
        Product existingProduct =  productRepository.findById(product.getProductId()).orElseThrow( () -> new ProductNotFoundException("PRODUCTO NO ENCONTRADO"));
        
        return itemRepository.save(product);

    }
   
}
