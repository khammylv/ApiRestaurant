package com.example.ApiRestaurant.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiRestaurant.entities.Cart;
import com.example.ApiRestaurant.entities.CartItem;
import com.example.ApiRestaurant.entities.Cliente;
import com.example.ApiRestaurant.entities.ItemProduct;
import com.example.ApiRestaurant.entities.DTO.CartDTO;
import com.example.ApiRestaurant.exception.ProductNotFoundException;
import com.example.ApiRestaurant.repositories.CartRepository;
import com.example.ApiRestaurant.repositories.ClienteRepository;
import com.example.ApiRestaurant.repositories.ItemProductRepository;

@Service
public class CartServices {
    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartItemServices cartItemServices;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ItemProductRepository itemProductRepository;

    @Autowired
    ItemProductServices itemProductServices;

    public List<Cart> getCart() {
        return cartRepository.findAll();
    }

    public Cart getCartProduct(Long id) {
        Optional<Cliente> opt = clienteRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ProductNotFoundException("USUARIO NO ENCONTRADO DEL USUARIO");
        }
        Optional<Cart> optCart= cartRepository.findById(id);
        if(optCart.isEmpty()){
           throw new ProductNotFoundException("CARRO NO ENCONTRADO DEL USUARIO"); 
        }

        return optCart.get();

    }

    public Cart addProductToCart2(ItemProduct itemProductproduct, Long id) {
        Optional<Cliente> opt = clienteRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ProductNotFoundException("USUARIO NO ENCONTRADO");
        }
        Cliente cliente = opt.get();
        Cart customerCart = cliente.getCustomerCart();
        List<ItemProduct> itemsP = customerCart.getItemProducts();
        ItemProduct item = itemProductServices.addItemProduct(itemProductproduct);
        if (itemsP.size() == 0) {
            itemsP.add(item);
            customerCart.setCartTotal(item.getPrice());
        } else {
            boolean flag = false;
            for (ItemProduct p : itemsP) {
                if (p.getProductId() == itemProductproduct.getProductId()) {
                    p.setCartItemQuantity(p.getCartItemQuantity() + 1);
                    customerCart.setCartTotal(customerCart.getCartTotal() + p.getPrice());
                    itemProductRepository.save(p);
                    flag = true;
                }

            }
            if (!flag) {
                itemsP.add(item);
                customerCart.setCartTotal(customerCart.getCartTotal() + item.getPrice());
            }
        }
        return cartRepository.save(cliente.getCustomerCart());

    }

    public Cart removeProductFromCart(ItemProduct itemProductproduct, Long id) {
        Optional<Cliente> opt = clienteRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ProductNotFoundException("USUARIO NO ENCONTRADO");
        }
        Cliente cliente = opt.get();
        Cart customerCart = cliente.getCustomerCart();
        List<ItemProduct> itemsP = customerCart.getItemProducts();
        if (itemsP.size() == 0) {
            throw new ProductNotFoundException("NO HAY PRODUCTOS EN EL CARRITO");
        }
        boolean flag = false;
        for (ItemProduct p : itemsP) {
            if (p.getProductId() == itemProductproduct.getProductId()) {
                p.setCartItemQuantity(p.getCartItemQuantity() - 1);
                customerCart.setCartTotal(customerCart.getCartTotal() - p.getPrice());
                if (p.getCartItemQuantity() == 0) {
                    itemsP.remove(p);
                    itemProductRepository.delete(p);
                    return cartRepository.save(customerCart);
                }
                flag = true;

            }

        }
        if (!flag) {
            throw new ProductNotFoundException("PRODUCTO NO AGREGADO EN EL CARRITO");
        }
        if (itemsP.size() == 0) {
            cartRepository.save(customerCart);
            throw new ProductNotFoundException("EL CARRITO ESTA VACIO");
        }
        return cartRepository.save(customerCart);

    }

    public Cart clearCart(Long id) {

        Optional<Cliente> opt = clienteRepository.findById(id);
        if (opt.isEmpty()) {
            throw new ProductNotFoundException("USUARIO NO ENCONTRADO");

        }
        Cliente existingCustomer = opt.get();
        Cart customerCart = existingCustomer.getCustomerCart();
        if (customerCart.getItemProducts().size() == 0) {
            throw new ProductNotFoundException("El carrito esta vacio ahora");
        }
        List<ItemProduct> emptyCart = new ArrayList<>();
        customerCart.setItemProducts(emptyCart);
        customerCart.setCartTotal(0.0);

        return cartRepository.save(customerCart);

    }

 
}
