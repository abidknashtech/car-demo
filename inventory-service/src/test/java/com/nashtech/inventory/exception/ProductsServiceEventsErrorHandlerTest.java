package com.nashtech.inventory.exception;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProductsServiceEventsErrorHandlerTest {
    @Test
    public void test_throws_same_exception() {
        // Arrange
        ProductsServiceEventsErrorHandler errorHandler = new ProductsServiceEventsErrorHandler();
        Exception exception = new Exception();
        EventMessage<?> event = mock(EventMessage.class);
        EventMessageHandler eventHandler = mock(EventMessageHandler.class);

        // Act and Assert
        assertThrows(Exception.class, () -> errorHandler.onError(exception, event, eventHandler));
    }
    @Test
    public void test_throws_any_exception() {
        // Arrange
        ProductsServiceEventsErrorHandler errorHandler = new ProductsServiceEventsErrorHandler();
        Exception exception = new RuntimeException();
        EventMessage<?> event = mock(EventMessage.class);
        EventMessageHandler eventHandler = mock(EventMessageHandler.class);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> errorHandler.onError(exception, event, eventHandler));
    }
    @Test
    public void test_throws_exception_when_event_is_null() {
        // Arrange
        ProductsServiceEventsErrorHandler errorHandler = new ProductsServiceEventsErrorHandler();
        Exception exception = new Exception();
        EventMessageHandler eventHandler = mock(EventMessageHandler.class);

        // Act and Assert
        assertThrows(Exception.class, () -> errorHandler.onError(exception, null, eventHandler));
    }
    @Test
    public void test_throws_exception_when_event_handler_is_null() {
        // Arrange
        ProductsServiceEventsErrorHandler errorHandler = new ProductsServiceEventsErrorHandler();
        Exception exception = new Exception();
        EventMessage<?> event = mock(EventMessage.class);

        // Act and Assert
        assertThrows(Exception.class, () -> errorHandler.onError(exception, event, null));
    }
    @Test
    public void test_throws_exception_when_both_event_and_event_handler_are_null() {
        // Arrange
        ProductsServiceEventsErrorHandler errorHandler = new ProductsServiceEventsErrorHandler();
        Exception exception = new Exception();

        // Act and Assert
        assertThrows(Exception.class, () -> errorHandler.onError(exception, null, null));
    }
}