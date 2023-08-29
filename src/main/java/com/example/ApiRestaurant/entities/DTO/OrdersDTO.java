package com.example.ApiRestaurant.entities.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class OrdersDTO {
   private LocalDate fechaComprado; 
   private LocalTime horaDePedido;
   
}
