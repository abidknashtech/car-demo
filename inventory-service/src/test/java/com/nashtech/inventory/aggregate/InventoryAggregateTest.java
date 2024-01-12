package com.nashtech.inventory.aggregate;

import com.nashtech.common.command.ReserveProductCommand;
import com.nashtech.common.event.ProductReserveFailedEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.inventory.events.ProductCreatedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InventoryAggregateTest {
//    private AggregateTestFixture<InventoryAggregate> fixture;
//
//    @BeforeEach
//    public void setUp() {
//        fixture = new AggregateTestFixture<>(InventoryAggregate.class);
//    }
//
//    @Test
//    public void test_reserve_product_successfully_with_enough_quantity() {
//        // Arrange
//        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
//                .productId("123")
//                .orderId("456")
//                .userId("789")
//                .quantity(5)
//                .build();
//
//        // Act & Assert
//        fixture
//                .given(new ProductCreatedEvent("123", "Brand", "Model", 2022, "Red", 100.0, 50000.0, 20, 10.0f))
//                .when(reserveProductCommand)
//                .expectSuccessfulHandlerExecution()
//                .expectEvents(new ProductReservedEvent("456", "123", "789", 5, "Brand", 50000.0, 10.0f, 50.0, 25050.0, 25050.0));
//    }
//
//    @Test
//    public void test_reserve_product_successfully_with_exact_quantity() {
//        // Arrange
//        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
//                .productId("123")
//                .orderId("456")
//                .userId("789")
//                .quantity(10)
//                .build();
//
//        // Act & Assert
//        fixture
//                .given(new ProductCreatedEvent("123", "Brand", "Model", 2022, "Red", 100.0, 50000.0, 20, 10.0f))
//                .when(reserveProductCommand)
//                .expectSuccessfulHandlerExecution()
//                .expectEvents(new ProductReservedEvent("456", "123", "789", 10, "Brand", 50000.0, 10.0f, 100.0, 50000.0, 50100.0));
//    }
//
//    @Test
//    public void test_reserve_product_successfully_with_minimum_quantity() {
//        // Arrange
//        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
//                .productId("123")
//                .orderId("456")
//                .userId("789")
//                .quantity(1)
//                .build();
//
//        // Act & Assert
//        fixture
//                .given(new ProductCreatedEvent("123", "Brand", "Model", 2022, "Red", 100.0, 50000.0, 20, 10.0f))
//                .when(reserveProductCommand)
//                .expectSuccessfulHandlerExecution()
//                .expectEvents(new ProductReservedEvent("456", "123", "789", 1, "Brand", 50000.0, 10.0f, 10.0, 5000.0, 5010.0));
//    }
//
//    @Test
//    public void test_fail_to_reserve_product_with_zero_quantity() {
//        // Arrange
//        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
//                .productId("123")
//                .orderId("456")
//                .userId("789")
//                .quantity(1)
//                .build();
//
//        // Act & Assert
//        fixture
//                .given(new ProductCreatedEvent("123", "Brand", "Model", 2022, "Red", 100.0, 50000.0, 0, 10.0f))
//                .when(reserveProductCommand)
//                .expectException(ProductReserveFailedEvent.class)
//                .expectEvents(new ProductReserveFailedEvent("456", "123", "789", 1, "Insufficient number of items in stock for product 123"));
//    }
//
//    @Test
//    public void test_fail_to_reserve_product_with_negative_quantity() {
//        // Arrange
//        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
//                .productId("123")
//                .orderId("456")
//                .userId("789")
//                .quantity(1)
//                .build();
//
//        // Act & Assert
//        fixture
//                .given(new ProductCreatedEvent("123", "Brand", "Model", 2022, "Red", 100.0, 50000.0, -1, 10.0f))
//                .when(reserveProductCommand)
//                .expectException(ProductReserveFailedEvent.class)
//                .expectEvents(new ProductReserveFailedEvent("456", "123", "789", 1, "Insufficient number of items in stock for product 123"));
//    }
//
//    @Test
//    public void test_fail_to_reserve_product_with_more_quantity_than_in_stock() {
//        // Arrange
//        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
//                .productId("123")
//                .orderId("456")
//                .userId("789")
//                .quantity(10)
//                .build();
//
//        // Act & Assert
//        fixture
//                .given(new ProductCreatedEvent("123", "Brand", "Model", 2022, "Red", 100.0, 50000.0, 5, 10.0f))
//                .when(reserveProductCommand)
//                .expectException(ProductReserveFailedEvent.class)
//                .expectEvents(new ProductReserveFailedEvent("456", "123", "789", 10, "Insufficient number of items in stock for product 123"));
//    }
}