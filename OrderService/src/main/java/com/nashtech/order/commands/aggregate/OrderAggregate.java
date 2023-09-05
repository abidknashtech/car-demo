package com.nashtech.order.commands.aggregate;

import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.commands.ApproveOrderCommand;
import com.nashtech.order.commands.CreateOrderCommand;
import com.nashtech.order.commands.RejectOrderCommand;
import com.nashtech.order.events.OrderApprovedEvent;
import com.nashtech.order.events.OrderCancelledEvent;
import com.nashtech.order.events.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private String carId;
    private String userId;
    private Integer quantity;
    private OrderStatus orderStatus;
    private Double price;

    private String reasonToRejectedOrder;

    public OrderAggregate() {
    }

    @CommandHandler
    public OrderAggregate(CreateOrderCommand createOrderCommand) {
        OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.builder()
                .orderId(createOrderCommand.getOrderId())
                .carId(createOrderCommand.getCarId())
                .price(createOrderCommand.getPrice())
                .quantity(createOrderCommand.getQuantity())
                .userId(createOrderCommand.getUserId())
                .orderStatus(OrderStatus.ORDER_CREATED)
                .build();
        AggregateLifecycle.apply(orderCreatedEvent);
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent orderCreatedEvent) {
        this.orderId = orderCreatedEvent.getOrderId();
        this.carId = orderCreatedEvent.getCarId();
        this.userId = orderCreatedEvent.getUserId();
        this.quantity = orderCreatedEvent.getQuantity();
        this.price = orderCreatedEvent.getPrice();
        this.orderStatus = orderCreatedEvent.getOrderStatus();
    }

    @CommandHandler
    public void handle(ApproveOrderCommand approveOrderCommand) {
        OrderApprovedEvent orderApprovedEvent = OrderApprovedEvent.builder()
                .orderId(approveOrderCommand.getOrderId())
                .orderStatus(approveOrderCommand.getOrderStatus())
                .build();
        AggregateLifecycle.apply(orderApprovedEvent);
    }

    @EventSourcingHandler
    public void on(OrderApprovedEvent event) {
        this.orderStatus = event.getOrderStatus();
    }

    @CommandHandler
    public void handle(RejectOrderCommand rejectOrderCommand) {
        OrderCancelledEvent orderCancelledEvent = OrderCancelledEvent.builder()
                .orderId(rejectOrderCommand.getOrderId())
                .orderStatus(rejectOrderCommand.getOrderStatus())
                .reason(rejectOrderCommand.getReasonToReject())
                .build();
        AggregateLifecycle.apply(orderCancelledEvent);
    }

    @EventSourcingHandler
    public void on(OrderCancelledEvent event) {
        this.orderStatus = event.getOrderStatus();
        this.reasonToRejectedOrder = event.getReason();
    }

}
