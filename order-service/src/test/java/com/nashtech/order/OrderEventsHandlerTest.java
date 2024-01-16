package com.nashtech.order;

import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.events.OrderApprovedEvent;
import com.nashtech.order.events.OrderCancelledEvent;
import com.nashtech.order.events.OrderCreatedEvent;
import com.nashtech.order.handler.OrderEventsHandler;
import com.nashtech.order.repository.FailedOrderRepository;
import com.nashtech.order.repository.OrderRepository;
import com.nashtech.order.repository.entity.OrderEntity;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderEventsHandlerTest {
    private static final String ORDER_ID = UUID.randomUUID().toString();
    private static final String PRODUCT_ID = UUID.randomUUID().toString();
    private static final String PAYMENT_ID = UUID.randomUUID().toString();
    private static final String SHIPMENT_ID = UUID.randomUUID().toString();
    private static final String USER_ID = "1652";


    private OrderRepository orderRepository = mock(OrderRepository.class);

    private FailedOrderRepository failedOrderRepository = mock(FailedOrderRepository.class);

    // @Autowired
    private OrderEventsHandler handler;

    @Before
    public void setUp() {
        handler = new OrderEventsHandler(orderRepository, failedOrderRepository);
    }


    @Test
    public void testOrderCreatedEventHandler() {
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                .orderId(ORDER_ID)
                .productId(PRODUCT_ID)
                .quantity(2)
                .userId(USER_ID)
                .orderStatus(OrderStatus.ORDER_PLACED)
                .build();
        handler.on(orderCreatedEvent);
    }

    @Test
    public void testOrderApprovedEventHandler() {
        OrderApprovedEvent orderApprovedEvent = OrderApprovedEvent.builder()
                .orderId(ORDER_ID)
                .paymentId(PAYMENT_ID)
                .shipmentId(SHIPMENT_ID)
                .orderStatus(OrderStatus.ORDER_PLACED)
                .build();

        OrderEntity order = new OrderEntity(orderApprovedEvent.getOrderId(), USER_ID, PRODUCT_ID,
                null, null, new Date(), orderApprovedEvent.getOrderStatus().toString());

        when(orderRepository.findByOrderId(ORDER_ID)).thenReturn(order);

        handler.on(orderApprovedEvent);
    }

    @Test
    public void testOrderCancelledEventHandler() {
        OrderCancelledEvent orderCancelledEvent = OrderCancelledEvent.builder()
                .orderId(ORDER_ID)
                .userId(USER_ID)
                .productId(PRODUCT_ID)
                .reasonToFailed("Car quantity not sufficient in inventory")
                .orderStatus(OrderStatus.ORDER_NOT_APPROVED)
                .build();
        handler.on(orderCancelledEvent);
    }
}
