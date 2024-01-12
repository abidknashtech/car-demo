package com.nashtech.inventory.aggregate;

import com.nashtech.common.command.ReserveProductCommand;
import com.nashtech.common.event.ProductReserveFailedEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.common.model.LineItem;
import com.nashtech.inventory.command.LineItemAddedCommand;
import com.nashtech.inventory.events.AddProductEvent;
import com.nashtech.inventory.events.LineItemsEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.HashMap;
import java.util.Map;

@Aggregate
@Slf4j
public class OrderLineAggregate {
    @AggregateIdentifier
    private String orderId;
    private Map<String, LineItem> orderLines = new HashMap<>();


    @CommandHandler
    public void handle(ReserveProductCommand reserveProductCommand) {
        HashMap<String,Integer> orderLines = reserveProductCommand.getOrderLines();
        log.info("ReserveProductCommand started with products {}",reserveProductCommand.getOrderLines().keySet());

        orderLines.forEach((productId,quantity)->{
            LineItemsEvent lineItemsEvent = LineItemsEvent.builder()
                    .orderId(reserveProductCommand.getOrderId())
                    .productId(productId)
                    .quantity(quantity)
                    .userId(reserveProductCommand.getUserId())
                    .build();
            AggregateLifecycle.apply(lineItemsEvent);
        });

        ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
                .orderId(reserveProductCommand.getOrderId())
                .orderLines(this.orderLines)
                .userId(reserveProductCommand.getUserId())
                .build();

        AggregateLifecycle.apply(productReservedEvent);
    }

    @CommandHandler
    public void handle(LineItemAddedCommand lineItemAddedCommand) {
        if (!lineItemAddedCommand.getInStock().keySet().stream().allMatch(available -> available)) {
            ProductReserveFailedEvent productFailedEvent = ProductReserveFailedEvent.builder()
                    .inStock(lineItemAddedCommand.getInStock())
                    .userId(lineItemAddedCommand.getUserId())
                    .orderId(lineItemAddedCommand.getOrderId())
                    .reasonToFailed("Insufficient number of items in stock for product ")
                    .build();
            AggregateLifecycle.apply(productFailedEvent);
            return;
        }

        LineItem lineItem = LineItem.builder()
                .productId(lineItemAddedCommand.getProductId())
                .quantity(lineItemAddedCommand.getQuantity())
                .brand(lineItemAddedCommand.getBrand())
                .basePrice(lineItemAddedCommand.getBasePrice())
                .tax(lineItemAddedCommand.getTax())
                .totalTax(lineItemAddedCommand.getTotalTax())
                .subTotal(lineItemAddedCommand.getSubTotal())
                .total(lineItemAddedCommand.getTotal())
                .model(lineItemAddedCommand.getModel())
                .mileage(lineItemAddedCommand.getMileage())
                .color(lineItemAddedCommand.getColor())
                .year(lineItemAddedCommand.getYear())
                .inStock(lineItemAddedCommand.getInStock())
                .build();

        AddProductEvent addProductEvent = AddProductEvent.builder()
                .orderId(lineItemAddedCommand.getOrderId())
                .lineItem(lineItem)
                .build();
        AggregateLifecycle.apply(addProductEvent);
    }

    @EventSourcingHandler
    public void on(AddProductEvent addProductEvent) {
        this.orderId = addProductEvent.getOrderId();
        this.orderLines.put(addProductEvent.getOrderId(),addProductEvent.getLineItem());
    }
}
