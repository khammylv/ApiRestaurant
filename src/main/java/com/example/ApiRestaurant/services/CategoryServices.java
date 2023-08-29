package com.example.ApiRestaurant.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ApiRestaurant.entities.Category;
import com.example.ApiRestaurant.repositories.CategoryRepository;

@Service
public class CategoryServices {
    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity <List<Category>> getCategories() {
        ResponseEntity<List<Category>> response = new ResponseEntity<List<Category>>(HttpStatus.OK);
        try{
            List<Category> categories = categoryRepository.findAll();
            response = new ResponseEntity<List<Category>>(categories, HttpStatus.OK);
            return response;

        }catch(Exception e){
            response = new ResponseEntity<List<Category>>(HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    public ResponseEntity<Category> newCategories(Category categories) {
        try{
           Category categoriesUpdated = categoryRepository.save(categories);
           return new ResponseEntity<Category>(categoriesUpdated, HttpStatus.OK);
        }
        catch(Exception e){
           return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }


  public ResponseEntity<Category> updateCategories(Category categories) {
        try{
           Category categoriesUpdated = categoryRepository.save(categories);
           return new ResponseEntity<Category>(categoriesUpdated, HttpStatus.OK);
        }
        catch(Exception e){
           return new ResponseEntity<Category>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
   }

     public  ResponseEntity<Object> getById(Long id) {
       HashMap<String, Object> result = new HashMap<>();
       boolean exist = categoryRepository.existsById(id);
       if( !exist ){
           result.put("error", true);
           result.put("message", "NO SE PUDO OBTENER LA CATEGORIA");
           return new ResponseEntity<>(
               result,
               HttpStatus.CONFLICT
           );
           }
          
          return new ResponseEntity<>(
               categoryRepository.findById(id),
               HttpStatus.ACCEPTED
           );
   }


   public ResponseEntity<Object> deleteCategories(Long id){
       HashMap<String, Object> result = new HashMap<>();
       boolean exist = categoryRepository.existsById(id);
       
       if( !exist ){
           result.put("error", true);
           result.put("message", "NO SE PUDO ELIMINAR LA CATEGORIA");
           return new ResponseEntity<>(
               result,
               HttpStatus.CONFLICT
           );
           }
           categoryRepository.deleteById(id);
           result.put("message", "CATEGORIA ELIMINADA");
           return new ResponseEntity<>(
               result,
               HttpStatus.ACCEPTED
           );

   }
   
   public Category getCategory(String name) {
     return categoryRepository.findByNameIgnoreCase(name);
       
   }
}
