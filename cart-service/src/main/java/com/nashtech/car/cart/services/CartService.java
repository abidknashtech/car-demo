package com.nashtech.car.cart.services;

import com.nashtech.car.cart.model.CartItem;
import com.nashtech.car.cart.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Transactional
    public void addToCart(Long carId, int quantity , String userId) {
        CartItem cartItem = cartItemRepository.findByCarId(carId);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCarId(carId);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(userId);
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
