package com.nashtech.car.cart.service;


import com.nashtech.car.cart.model.CartItem;
import com.nashtech.car.cart.repository.CartItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

class CartServiceTest {

    @Mock
    private CartItemRepository cartItemRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetFromCart() {
        CartItem cartItem = new CartItem();
        cartItem.setProductId("123");
        List<CartItem> cartItemList = Collections.singletonList(cartItem);
        Mockito.when(cartItemRepository.findByUserId(anyString())).thenReturn(cartItemList);

        List<CartItem> result = cartService.getFromCart("user1");

        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("123", result.get(0).getProductId());
    }
}

