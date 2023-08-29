package com.example.ApiRestaurant.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ApiRestaurant.entities.Orders;

public interface  OrderRepository extends JpaRepository<Orders, Long> {
    
}
