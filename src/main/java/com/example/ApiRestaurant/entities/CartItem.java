package com.example.ApiRestaurant.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "cartItem")
public class CartItem {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartItemId;
    
     @OneToOne
     @JsonIgnoreProperties(value={"productId", "rating", "description", "categorias"})
     private Product cartProduct;

    private Integer cartItemQuantity;
}
