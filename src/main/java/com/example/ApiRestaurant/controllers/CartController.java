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

import com.example.ApiRestaurant.entities.Cart;
import com.example.ApiRestaurant.entities.CartItem;
import com.example.ApiRestaurant.entities.DTO.CartDTO;
//import com.example.ApiRestaurant.services.CartInterface;
import com.example.ApiRestaurant.services.CartItemServices;
import com.example.ApiRestaurant.services.CartServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/cart")
public class CartController {
     @Autowired
     CartServices cartServices;

    //  @Autowired
    //  CartInterface cartInterface;

    @Autowired
    CartItemServices cartItemServices;


    // @PostMapping("agregar/{id}")
    // public ResponseEntity<Cart> addCart(@RequestBody CartDTO cartdto,  @PathVariable("id") Long id){
    //     Cart cart = cartInterface.addProductToCart(cartdto, id);
    //     return new ResponseEntity<Cart>(cart,HttpStatus.CREATED);
    // }

    @PostMapping("agregar/{id}")
    public ResponseEntity<Cart> addCart(@RequestBody CartDTO cartdto,  @PathVariable("id") Long id){
        Cart cart = cartServices.addProductToCart(cartdto, id);
        return new ResponseEntity<Cart>(cart,HttpStatus.CREATED);
    }

    @GetMapping()
    public List<Cart> getCartList(){
        return cartServices.getCart();
    }
    
    // @PostMapping("item")
    // public ResponseEntity<CartItem> addCart2(@RequestBody CartDTO cartdto){
    //     CartItem cart = cartItemServices.createItemforCart(cartdto);
    //     return new ResponseEntity<CartItem>(cart,HttpStatus.CREATED);
    // }


}
