package com.nashtech.order.handler;

import com.nashtech.order.events.OrderApprovedEvent;
import com.nashtech.order.events.OrderCancelledEvent;
import com.nashtech.order.events.OrderCreatedEvent;
import com.nashtech.order.repository.FailedOrderRepository;
import com.nashtech.order.repository.OrderRepository;
import com.nashtech.order.repository.entity.FailedOrder;
import com.nashtech.order.repository.entity.Order;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@ProcessingGroup("order-group")
@Slf4j
public class OrderEventsHandler {

    private final OrderRepository orderRepository;
    private final FailedOrderRepository failedOrderRepository;

    public OrderEventsHandler(OrderRepository orderRepository, FailedOrderRepository failedOrderRepository) {
        this.orderRepository = orderRepository;
        this.failedOrderRepository = failedOrderRepository;
    }

    @EventHandler
    public void on(OrderCreatedEvent event) {
        Order order = new Order(event.getOrderId(), event.getUserId(), event.getProductId(),
                null, null, new Date(),event.getOrderStatus().toString());
        orderRepository.save(order);

        FailedOrder failedOrder = new FailedOrder();
        failedOrder.setOrderId(event.getOrderId());
        failedOrderRepository.save(failedOrder);
    }

    @EventHandler
    public void on(OrderApprovedEvent orderApprovedEvent) {
        Order order = orderRepository.findByOrderId((orderApprovedEvent.getOrderId()));
        order.setPaymentId(orderApprovedEvent.getPaymentId());
        order.setShipmentId(orderApprovedEvent.getShipmentId());
        order.setOrderStatus(orderApprovedEvent.getOrderStatus().toString());
        orderRepository.save(order);

        FailedOrder failedOrder = new FailedOrder();
        failedOrder.setOrderId(orderApprovedEvent.getOrderId());
        failedOrderRepository.delete(failedOrder);
    }

    @EventHandler
    public void on(OrderCancelledEvent event) {
        Optional<FailedOrder> orderOptional = failedOrderRepository.findById((event.getOrderId()));
        if(orderOptional.isEmpty()) {
            log.error("Order failed  status did not persist {}",event.getOrderId());
            return;
        }
        FailedOrder order = orderOptional.get();
        order.setProductId(event.getProductId());
        order.setPaymentId(event.getPaymentId());
        order.setShipmentId(event.getShipmentId());
        order.setOrderStatus(event.getOrderStatus().toString());
        order.setReasonToFailed(event.getReasonToFailed());
        failedOrderRepository.save(order);
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        log.error("{} occurred during order saving", exception.getMessage());
        throw exception;
    }

}
