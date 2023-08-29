package com.example.ApiRestaurant.entities.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartDTO {
    @NotNull
	private Long productId;
	
	private String productName;
	
	private Double price;
	
	
}
