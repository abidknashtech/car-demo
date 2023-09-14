package com.nashtech.car.cart.services;

import com.nashtech.car.cart.model.CartItem;
import com.nashtech.car.cart.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

   // @Transactional
    public CartItem addToCart(String productId, int quantity , String userId) {
        CartItem cartItem = cartItemRepository.findByProductIdAndUserId(productId,userId);

        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setProductId(productId);
            cartItem.setQuantity(quantity);
            cartItem.setUserId(userId);
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        return cartItemRepository.save(cartItem);
    }

    public CartItem removeFromCart(String productId, int quantity,String userId) {
        CartItem cartItem = cartItemRepository.findByProductIdAndUserId(productId,userId);

        if (cartItem != null) {
            int updatedQuantity = cartItem.getQuantity() - quantity;
            if (updatedQuantity <= 0) {
                cartItemRepository.delete(cartItem);
                throw new IllegalStateException("Product removed from cart");
            } else {
                cartItem.setQuantity(updatedQuantity);
                cartItem= cartItemRepository.save(cartItem);
            }
        }
        return cartItem;
    }

    @Transactional
    public CartItem getFromCart(String productId, String userId) {
        CartItem cartItem = cartItemRepository.findByProductIdAndUserId(productId,userId);
        if (cartItem == null) {
            throw new IllegalStateException("Product not found from the cart");
        }
        return cartItem;
    }

}
