package com.example.ApiRestaurant.entities;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "clientes")
public class Cliente {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clienteId;

    private String nombre;

	private String apellido;

    private String username;

    @OneToOne(cascade = CascadeType.ALL)
	private Cart customerCart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
	private List<Orders> orders = new ArrayList<>();
}
