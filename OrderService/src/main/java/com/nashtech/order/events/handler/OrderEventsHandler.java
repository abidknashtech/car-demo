package com.nashtech.order.events.handler;

import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.events.OrderApprovedEvent;
import com.nashtech.order.events.OrderCancelledEvent;
import com.nashtech.order.events.OrderCreatedEvent;
import com.nashtech.order.repository.OrderRepository;
import com.nashtech.order.repository.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ProcessingGroup("order-group")
@Slf4j
public class OrderEventsHandler {

    private final OrderRepository orderRepository;

    public OrderEventsHandler(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        Order order = new Order(event.getOrderId(), event.getCarId(), event.getUserId(), event.getPrice(),
                event.getQuantity(), event.getOrderStatus().toString(), new Date(), null);
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderApprovedEvent orderApprovedEvent) {
        Order order = orderRepository.findByOrderId((orderApprovedEvent.getOrderId()));
        order.setOrderStatus(OrderStatus.ORDER_APPROVED.toString());
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCancelledEvent event) {
        Order order = orderRepository.findByOrderId((event.getOrderId()));
        order.setOrderStatus(event.getOrderStatus().toString());
        order.setReasonToRejectedOrder(event.getReason());
        orderRepository.save(order);
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        log.error("{} occurred during order saving", exception.getMessage());
        throw exception;
    }

}
