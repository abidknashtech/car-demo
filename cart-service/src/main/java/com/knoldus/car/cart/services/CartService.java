package com.knoldus.car.cart.services;

import com.knoldus.car.cart.model.CartItem;
import com.knoldus.car.cart.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public void addToCart(Long carId, int quantity) {
        CartItem cartItem = cartItemRepository.findByCarId(carId);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCarId(carId);
            cartItem.setQuantity(quantity);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        cartItemRepository.save(cartItem);

        Iterable<CartItem> todos = cartItemRepository.findAll();
        todos.forEach(System.out::println);
    }

    public void removeFromCart(Long carId, int quantity) {
        CartItem cartItem = cartItemRepository.findByCarId(carId);

        if (cartItem != null) {
            int updatedQuantity = cartItem.getQuantity() - quantity;
            if (updatedQuantity <= 0) {
                cartItemRepository.delete(cartItem);
            } else {
                cartItem.setQuantity(updatedQuantity);
                cartItemRepository.save(cartItem);
            }
        }
    }
}
