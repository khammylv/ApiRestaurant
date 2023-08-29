package com.example.ApiRestaurant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiRestaurant.entities.Product;
import com.example.ApiRestaurant.services.ProductServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/products")
public class ProductController {
    @Autowired
    ProductServices productService;

     @GetMapping()
    public ResponseEntity<List<Product>> getProducts() {
        return productService.getProducts();
     }
   

     @PostMapping(path = "/{id}")
     public Product postProducts(@RequestBody Product product, @PathVariable("id") Long id) {
        return productService.newProduct(product, id);
     }
     

     @PostMapping("agregar/{catenom}")
     public Product createProduct(@RequestBody Product product, @PathVariable("catenom") String name) {
           return productService.newProductByCategory(product, name);
     }

}
