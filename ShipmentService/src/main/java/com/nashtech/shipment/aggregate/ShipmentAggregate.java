package com.nashtech.shipment.aggregate;

import com.nashtech.common.command.CancelShipmentCommand;
import com.nashtech.common.command.CreatedShipmentCommand;
import com.nashtech.common.event.ShipmentCancelledEvent;
import com.nashtech.common.event.ShipmentCreatedEvent;
import com.nashtech.common.model.ShipmentStatus;
import com.nashtech.shipment.handler.ShipmentEventHandler;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aggregate
public class ShipmentAggregate {

    private final Logger LOGGER = LoggerFactory.getLogger(ShipmentEventHandler.class);

    @AggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String productId;
    private Integer quantity;
    private Double price;
    private String userId;
    private String reasonToFailed;
    private String paymentId;
    private ShipmentStatus shipmentStatus;

    public ShipmentAggregate() {
    }

    @CommandHandler
    public ShipmentAggregate(CreatedShipmentCommand createdShipmentCommand) {
        LOGGER.info("CreatedShipmentCommand is called for shipmentId: {}", createdShipmentCommand.getShipmentId());
        ShipmentCreatedEvent shipmentCreatedEvent = ShipmentCreatedEvent.builder()
                    .shipmentId(createdShipmentCommand.getShipmentId())
                    .orderId(createdShipmentCommand.getOrderId())
                    .productId(createdShipmentCommand.getProductId())
                    .quantity(createdShipmentCommand.getQuantity())
                    .price(createdShipmentCommand.getPrice())
                    .userId(createdShipmentCommand.getUserId())
                    .paymentId(createdShipmentCommand.getPaymentId())
                    .shipmentStatus(createdShipmentCommand.getShipmentStatus()).build();
        AggregateLifecycle.apply(shipmentCreatedEvent);
    }

    @CommandHandler
    public void handle(CancelShipmentCommand cancelShipmentCommand) {
        LOGGER.info("CancelShipmentCommand is called for shipmentId: {}", cancelShipmentCommand.getShipmentId());
        ShipmentCancelledEvent shipmentCancelledEvent = ShipmentCancelledEvent.builder()
                .shipmentId(cancelShipmentCommand.getShipmentId())
                .orderId(cancelShipmentCommand.getOrderId())
                .productId(cancelShipmentCommand.getProductId())
                .quantity(cancelShipmentCommand.getQuantity())
                .price(cancelShipmentCommand.getPrice())
                .userId(cancelShipmentCommand.getUserId())
                .reasonToFailed(cancelShipmentCommand.getReasonToFailed())
                .paymentId(cancelShipmentCommand.getPaymentId())
                .shipmentStatus(cancelShipmentCommand.getShipmentStatus()).build();
        AggregateLifecycle.apply(shipmentCancelledEvent);
    }

    @EventSourcingHandler
    public  void on(ShipmentCreatedEvent shipmentCreatedEvent) {
        this.shipmentId = shipmentCreatedEvent.getShipmentId();
        this.orderId = shipmentCreatedEvent.getOrderId();
        this.productId = shipmentCreatedEvent.getProductId();
        this.quantity = shipmentCreatedEvent.getQuantity();
        this.price = shipmentCreatedEvent.getPrice();
        this.userId = shipmentCreatedEvent.getUserId();
        this.paymentId = shipmentCreatedEvent.getPaymentId();
        this.shipmentStatus = shipmentCreatedEvent.getShipmentStatus();
    }

    @EventSourcingHandler
    public void on(ShipmentCancelledEvent shipmentCancelledEvent) {
        this.shipmentId = shipmentCancelledEvent.getShipmentId();
        this.orderId = shipmentCancelledEvent.getOrderId();
        this.productId = shipmentCancelledEvent.getProductId();
        this.quantity = shipmentCancelledEvent.getQuantity();
        this.price = shipmentCancelledEvent.getPrice();
        this.userId = shipmentCancelledEvent.getUserId();
        this.reasonToFailed = shipmentCancelledEvent.getReasonToFailed();
        this.paymentId = shipmentCancelledEvent.getPaymentId();
        this.shipmentStatus = shipmentCancelledEvent.getShipmentStatus();
    }
}
