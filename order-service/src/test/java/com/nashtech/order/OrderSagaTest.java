package com.nashtech.order;

import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.events.OrderCreatedEvent;
import com.nashtech.order.saga.OrderSaga;
import org.axonframework.test.matchers.Matchers;
import org.axonframework.test.saga.FixtureConfiguration;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.Test;

import java.time.Duration;
import java.util.UUID;

public class OrderSagaTest {
    private static final String ORDER_ID = UUID.randomUUID().toString();
    private static final String PRODUCT_ID = UUID.randomUUID().toString();
    private static final String PAYMENT_ID = UUID.randomUUID().toString();
    private static final String SHIPMENT_ID = UUID.randomUUID().toString();
    private static final String USER_ID = "1652";
    private static final FixtureConfiguration fixture = new SagaTestFixture<>(OrderSaga.class);

    @Test
    public void testOrderCreatedEvent() {
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                .orderId(ORDER_ID)
                .productId(PRODUCT_ID)
                .quantity(2)
                .userId(USER_ID)
                .orderStatus(OrderStatus.ORDER_PARTIALLY_APPROVED)
                .build();

        fixture.givenAggregate(ORDER_ID)
                .published(orderCreatedEvent)
                .whenTimeElapses(Duration.ofDays(31))
                .expectDispatchedCommandsMatching(Matchers.listWithAllOf());
    }

    @Test
    public void testProductReservedEvent() {
        ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
                .orderId(ORDER_ID)
                .productId(PRODUCT_ID)
                .userId(USER_ID)
                .quantity(2)
                .brand("Tata")
                .basePrice(30000.0)
                .tax(0.03f)
                .totalTax(0.06f)
                .subTotal(60000.0)
                .total(60000.06)
                .model("model")
                .mileage(12d)
                .color("Red")
                .year(2024)
                .build();

        fixture.givenAggregate(ORDER_ID)
                .published(productReservedEvent)
                .whenTimeElapses(Duration.ofDays(31))
                .expectDispatchedCommandsMatching(Matchers.listWithAllOf());
    }
}
