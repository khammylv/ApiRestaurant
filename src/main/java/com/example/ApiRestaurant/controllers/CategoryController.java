package com.example.ApiRestaurant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiRestaurant.entities.Category;
import com.example.ApiRestaurant.services.CategoryServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/categories")
public class CategoryController {
  
     @Autowired
    CategoryServices categoryService;

     @GetMapping
     public ResponseEntity<List<Category>> getCategories() {
        return categoryService.getCategories();
     }

      @PostMapping()
     public ResponseEntity<Category> postCategories(@RequestBody Category category) {
        return categoryService.newCategories(category);
     }

     

  
     @GetMapping(path = "/{id}")
     public ResponseEntity<Object> obtenerProductsPorId(@PathVariable("id") Long id) {
        return categoryService.getById(id);
     }

     @DeleteMapping(path = "/{id}")
     public ResponseEntity<Object> eliminarPorId(@PathVariable("id") Long id) {
        return categoryService.deleteCategories(id);
     }
     

      @GetMapping( "/category/{catenom}")
      public Category getCategoriesByName(@PathVariable("catenom") String name){
          return categoryService.getCategory(name);
      }


}
