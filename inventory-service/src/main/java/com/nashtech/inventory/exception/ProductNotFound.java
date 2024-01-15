package com.nashtech.inventory.exception;

public class ProductNotFound extends RuntimeException {
    private String message;

    public ProductNotFound(String message) {
        super(message);
        this.message = message;
    }

}
