package com.example.ApiRestaurant.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.ApiRestaurant.entities.Cliente;
import com.example.ApiRestaurant.entities.ItemProduct;
import com.example.ApiRestaurant.entities.OrderStatus;
import com.example.ApiRestaurant.entities.Orders;
import com.example.ApiRestaurant.entities.DTO.OrdersDTO;
import com.example.ApiRestaurant.exception.ProductNotFoundException;
import com.example.ApiRestaurant.repositories.ClienteRepository;
import com.example.ApiRestaurant.repositories.OrderRepository;

@Service
public class OrderServices {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClienteServices clienteServices;

    @Autowired
    CartServices cartServices;

    @Autowired
    private ClienteRepository clienteRepository;
    
    public Orders saveOrder(OrdersDTO odto, Long id){
        Orders newOrder= new Orders();
             Optional<Cliente> opt = clienteRepository.findById(id);
         if (!opt.isPresent()) {
            throw new ProductNotFoundException("USUARIO NO ENCONTRADO");
        }
        Cliente cliente = opt.get();
        newOrder.setCliente(cliente);
        List<ItemProduct> productsInCart = cliente.getCustomerCart().getItemProducts();
        List<ItemProduct> productsInOrder = new ArrayList<>(productsInCart);
        newOrder.setOrdercartItems(productsInOrder);
        newOrder.setTotal(cliente.getCustomerCart().getCartTotal());
        if(productsInCart.size() != 0){
           newOrder.setFechaComprado(odto.getFechaComprado());
           newOrder.setHoraDePedido(odto.getHoraDePedido());
           newOrder.setHoraDeEntregado(odto.getHoraDePedido());
           newOrder.setStatus(OrderStatus.pending);
           
           cartServices.clearCart(id);
           return orderRepository.save(newOrder);
        }
        else{
          throw new ProductNotFoundException("NO HAY PRODUCTOS EN EL CARRO");
        }
        

    }
   
    public List<Orders> getOrders(){
        List<Orders> orders = orderRepository.findAll();
        if(orders.size() > 0){
          return orders;
        }
          else{
            throw new ProductNotFoundException("NO EXISTEN ORDENES EN TU CUENTA");
          }   

        
    }
}
