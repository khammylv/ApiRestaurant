package com.example.ApiRestaurant.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ApiRestaurant.entities.Cliente;
import com.example.ApiRestaurant.services.ClienteServices;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/cliente")
public class ClienteController {
    
     @Autowired
     ClienteServices clienteServices;


     @PostMapping
    public ResponseEntity<Cliente> postCliente(@RequestBody Cliente cliente){
          return clienteServices.newCliente(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getCliente() {
        return clienteServices.getClientes();
    }
}
