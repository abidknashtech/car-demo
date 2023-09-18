package com.nashtech.car.cart.controller;

import com.nashtech.car.cart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long carId, @RequestParam int quantity,
                                            @RequestParam String userId) {
        cartService.addToCart(carId, quantity,userId);
        return ResponseEntity.ok("Car added to cart.");
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeFromCart(@RequestParam Long carId, @RequestParam int quantity) {
        cartService.removeFromCart(carId, quantity);
        return ResponseEntity.ok("Car removed from cart.");
    }
}