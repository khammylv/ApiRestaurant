package com.example.ApiRestaurant.exception;

public class ProductNotFoundException extends RuntimeException{

	public ProductNotFoundException() {
		
	}
	
	public ProductNotFoundException(String message){
		super(message);
	}
}
