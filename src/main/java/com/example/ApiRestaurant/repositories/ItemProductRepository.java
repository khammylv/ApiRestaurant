package com.example.ApiRestaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiRestaurant.entities.ItemProduct;

public interface ItemProductRepository extends  JpaRepository<ItemProduct, Long> {
    
}
