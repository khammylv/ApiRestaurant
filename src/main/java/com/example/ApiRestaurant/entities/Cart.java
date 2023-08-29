package com.example.ApiRestaurant.entities;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "cart")
public class Cart {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cartId;

    // @OneToMany(cascade = CascadeType.ALL)
	// private List<CartItem> cartItems = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
	private List<ItemProduct> itemProducts = new ArrayList<>();

    private Double cartTotal;

    @OneToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value={ "nombre", "apellido", "username", "customerCart", "orders"})
    private Cliente cliente;
}
