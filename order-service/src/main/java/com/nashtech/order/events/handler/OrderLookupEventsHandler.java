package com.nashtech.order.events.handler;

import com.nashtech.order.events.OrderCreatedEvent;
import com.nashtech.order.repository.OrderLookupRepository;
import com.nashtech.order.repository.entity.OrderLookup;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@ProcessingGroup("order-group")
@Slf4j
public class OrderLookupEventsHandler {
    private final OrderLookupRepository orderLookupRepository;

    public OrderLookupEventsHandler(OrderLookupRepository orderLookupRepository) {
        this.orderLookupRepository = orderLookupRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        OrderLookup orderLookup = new OrderLookup(event.getOrderId(), new Date());
        orderLookupRepository.save(orderLookup);
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        log.error("{} occurred during OrderLookup", exception.getMessage());
        throw exception;
    }

}