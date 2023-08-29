package com.example.ApiRestaurant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiRestaurant.entities.Orders;
import com.example.ApiRestaurant.entities.DTO.OrdersDTO;
import com.example.ApiRestaurant.services.OrderServices;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/order")
public class OrdersController {
    

    @Autowired
    OrderServices orderServices;
    
    // @PostMapping("/{id}")
    // public ResponseEntity<Orders> addOrder(@Valid @RequestBody OrdersDTO odto,@PathVariable("id") Long id){
    //     Orders order = orderServices.saveOrder(odto, id);
    //     return new ResponseEntity<Orders>(order, HttpStatus.CREATED);
    // }

    // @GetMapping()
    // public List<Orders> getOrders(){
    //     List<Orders> orders = orderServices.getOrders();
    //     return orders;
    // }

}
