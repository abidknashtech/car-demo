package com.nashtech.inventory.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProductsErrorHandlerTest {
    @Test
    public void test_handleOtherExceptions_exception() {
        Exception ex = new Exception("Test Exception");
        ProductsErrorHandler errorHandler = new ProductsErrorHandler();
        ResponseEntity<Object> response = errorHandler.handleOtherExceptions(ex);
        ErrorMessage errorMessage = (ErrorMessage) response.getBody();

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNotNull(errorMessage);
        assertEquals(new Date().getTime(), errorMessage.getTimestamp().getTime(), 1000);
        assertEquals("Test Exception", errorMessage.getMessage());
    }
    @Test
    public void test_handleOtherExceptions_httpStatus() {
        Exception ex = new Exception("Test Exception");
        ProductsErrorHandler errorHandler = new ProductsErrorHandler();
        ResponseEntity<Object> response = errorHandler.handleOtherExceptions(ex);

        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

}