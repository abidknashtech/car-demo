package com.nashtech.order.exception;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String s) {
        super(s);
    }
}
