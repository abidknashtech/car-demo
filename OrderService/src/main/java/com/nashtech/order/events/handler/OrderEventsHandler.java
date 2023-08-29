package com.nashtech.order.events.handler;

import com.nashtech.order.events.OrderCancelledEvent;
import com.nashtech.order.events.OrderCreatedEvent;
import com.nashtech.order.repository.entity.Order;
import com.nashtech.order.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

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
        Order order = new Order();
        BeanUtils.copyProperties(event,order);
        order.setOrderStatus(event.getOrderStatus().toString());
        orderRepository.save(order);
    }

    @EventHandler
    public void on(OrderCancelledEvent event) {
        Order order = orderRepository.findById(event.getOrderId()).get();
        order.setOrderStatus(event.getOrderStatus().toString());
        orderRepository.save(order);
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }

}
