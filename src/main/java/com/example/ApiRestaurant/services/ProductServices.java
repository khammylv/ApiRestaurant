package com.example.ApiRestaurant.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ApiRestaurant.entities.Category;
import com.example.ApiRestaurant.entities.Product;

import com.example.ApiRestaurant.repositories.CategoryRepository;
import com.example.ApiRestaurant.repositories.ProductRepository;

@Service
public class ProductServices {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

     public ResponseEntity <List<Product>> getProducts() {
        ResponseEntity<List<Product>> response = new ResponseEntity<List<Product>>(HttpStatus.OK);
        try{
            List<Product> products = productRepository.findAll();
            response = new ResponseEntity<List<Product>>(products, HttpStatus.OK);
            return response;

        }catch(Exception e){
            response = new ResponseEntity<List<Product>>(HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    public Product newProduct(Product product, Long id){
        Product prod = null;
        Optional<Category> cat =  categoryRepository.findById(id);
        if(cat.isPresent()) {
            Category category = cat.get();
            product.setCategorias(category);
            prod = productRepository.save(product);
            category.getProducts().add(product);
            categoryRepository.save(category);
        }
        return prod;
    }

    public Product newProductByCategory(Product product, String name){
         Product prod = null;
          Category cat = categoryRepository.findByNameIgnoreCase(name);
          if(cat != null){
            Long id = cat.getId();
            Optional<Category> cate =  categoryRepository.findById(id);
            if(cate.isPresent()){
            Category category = cate.get();
            product.setCategorias(category);
            prod = productRepository.save(product);
            category.getProducts().add(product);
            categoryRepository.save(category);
            }
         
          }

          return prod;
    }
}
