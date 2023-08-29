package com.example.ApiRestaurant.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.ApiRestaurant.entities.Cart;
import com.example.ApiRestaurant.entities.Cliente;
import com.example.ApiRestaurant.entities.Orders;
import com.example.ApiRestaurant.repositories.ClienteRepository;

@Service
public class ClienteServices {
     @Autowired
    ClienteRepository clienteRepository;

     public ResponseEntity <List<Cliente>> getClientes() {
        ResponseEntity<List<Cliente>> response = new ResponseEntity<List<Cliente>>(HttpStatus.OK);
        try{
            List<Cliente> cliente = clienteRepository.findAll();
            response = new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
            return response;

        }catch(Exception e){
            response = new ResponseEntity<List<Cliente>>(HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }


    public ResponseEntity<Cliente> newCliente(Cliente cliente) {
        try{
            Cart c = new Cart();
            cliente.setCustomerCart(c);
            cliente.setOrders(new ArrayList<Orders>());
            Cliente productUpdated = clienteRepository.save(cliente);
           return new ResponseEntity<Cliente>(productUpdated, HttpStatus.OK);
        }
        catch(Exception e){
           return new ResponseEntity<Cliente>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
      }
}
