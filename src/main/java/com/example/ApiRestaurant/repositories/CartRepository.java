package com.example.ApiRestaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ApiRestaurant.entities.Cart;

@Repository
public interface CartRepository extends  JpaRepository<Cart, Long>{
    
}
