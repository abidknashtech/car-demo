package com.nashtech.inventory.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class ProductNotFoundTest {
    @Test
    void testNewProductNotFound() {
        // Arrange and Act
        ProductNotFound actualProductNotFound = new ProductNotFound("Not all who wander are lost");

        // Assert
        assertEquals("Not all who wander are lost", actualProductNotFound.getLocalizedMessage());
        assertEquals("Not all who wander are lost", actualProductNotFound.getMessage());
        assertNull(actualProductNotFound.getCause());
        assertEquals(0, actualProductNotFound.getSuppressed().length);
    }
}
