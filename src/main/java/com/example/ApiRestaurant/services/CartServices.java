package com.example.ApiRestaurant.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ApiRestaurant.entities.Cart;
import com.example.ApiRestaurant.entities.CartItem;
import com.example.ApiRestaurant.entities.Cliente;
import com.example.ApiRestaurant.entities.DTO.CartDTO;
import com.example.ApiRestaurant.exception.ProductNotFoundException;
import com.example.ApiRestaurant.repositories.CartRepository;
import com.example.ApiRestaurant.repositories.ClienteRepository;


@Service
public class CartServices {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    CartItemServices cartItemServices;

  

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cart> getCart() {
        return cartRepository.findAll();
    }

    public Cart addProductToCart(CartDTO cartDto, Long id) {
        Optional<Cliente> opt = clienteRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ProductNotFoundException("USUARIO NO ENCONTRADO");
        }
        Cliente cliente = opt.get();
        Cart customerCart = cliente.getCustomerCart();
        List<CartItem> cartItems = customerCart.getCartItems();
        CartItem item = cartItemServices.createItemforCart(cartDto);
        if (cartItems.size() == 0) {
            cartItems.add(item);
            customerCart.setCartTotal(item.getCartProduct().getPrice());
        } else {
            boolean flag = false;
            for (CartItem c : cartItems) {
                if (c.getCartProduct().getId() == cartDto.getId()) {
                    c.setCartItemQuantity(c.getCartItemQuantity() + 1);
                    customerCart.setCartTotal(customerCart.getCartTotal() + c.getCartProduct().getPrice());
                    flag = true;
                }
            }
            if (!flag) {
                cartItems.add(item);
                customerCart.setCartTotal(customerCart.getCartTotal() + item.getCartProduct().getPrice());
            }

        }
        return cartRepository.save(cliente.getCustomerCart());
    }


    public Cart removeProductFromCart(CartDTO cartDto,Long id) {
         Optional<Cliente> opt = clienteRepository.findById(id);
        if (!opt.isPresent()) {
            throw new ProductNotFoundException("USUARIO NO ENCONTRADO");
        }
         Cliente cliente = opt.get();
        Cart customerCart = cliente.getCustomerCart();
        List<CartItem> cartItems = customerCart.getCartItems();
        CartItem item = cartItemServices.createItemforCart(cartDto);
        if(cartItems.size() == 0) {
            throw new ProductNotFoundException("NO HAY PRODUCTOS EN EL CARRITO");
        }
        boolean flag = false;
        for(CartItem c: cartItems) {
            if (c.getCartProduct().getId() == cartDto.getId()) {
                c.setCartItemQuantity(c.getCartItemQuantity() - 1);
                customerCart.setCartTotal(customerCart.getCartTotal() - c.getCartProduct().getPrice());
				if(c.getCartItemQuantity() == 0) {
					
					cartItems.remove(c);
                    return cartRepository.save(customerCart);
                }
                flag = true;
            }
        }
        if(!flag){
            throw new ProductNotFoundException("ESTE PRODUCTO NO ESTA EN EL CARRO");
        }

        if(cartItems.size() == 0){
            cartRepository.save(customerCart);
            throw new ProductNotFoundException("El carrito esta vacio ahora");
        }
        return cartRepository.save(customerCart);
        
    }
    
    public Cart clearCart(Long id) {

       Optional<Cliente> opt = clienteRepository.findById(id);
       if(opt.isEmpty()){
         throw new ProductNotFoundException("USUARIO NO ENCONTRADO");
      
       }
           
        Cliente existingCustomer = opt.get();
        Cart customerCart = existingCustomer.getCustomerCart();

        if(customerCart.getCartItems().size() == 0) {
			throw new ProductNotFoundException("El carrito esta vacio ahora");
		}
		List<CartItem> emptyCart = new ArrayList<>();
        customerCart.setCartItems(emptyCart);
        customerCart.setCartTotal(0.0);

        return cartRepository.save(customerCart);

    }
}
