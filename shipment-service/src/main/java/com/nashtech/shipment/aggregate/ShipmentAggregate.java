package com.nashtech.shipment.aggregate;

import com.nashtech.common.command.CreatedShipmentCommand;
import com.nashtech.common.event.ShipmentCreatedEvent;
import com.nashtech.common.model.ShipmentStatus;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
public class ShipmentAggregate {

    @AggregateIdentifier
    private String shipmentId;
    private String orderId;
    private String productId;
    private Integer quantity;
    private Double price;
    private Double subTotal;
    private Double grandTotal;
    private Float tax;
    private String userId;
    private String firstName;
    private String lastName;
    private String address;
    private String paymentId;
    private ShipmentStatus shipmentStatus;

    public ShipmentAggregate() {
    }

    @CommandHandler
    public ShipmentAggregate(CreatedShipmentCommand createdShipmentCommand) {
        log.info("CreatedShipmentCommand is called for shipmentId: {}", createdShipmentCommand.getShipmentId());
        ShipmentCreatedEvent shipmentCreatedEvent = ShipmentCreatedEvent.builder()
                .shipmentId(createdShipmentCommand.getShipmentId())
                .orderId(createdShipmentCommand.getOrderId())
                .productId(createdShipmentCommand.getProductId())
                .quantity(createdShipmentCommand.getQuantity())
                .price(createdShipmentCommand.getPrice())
                .subTotal(createdShipmentCommand.getSubTotal())
                .grandTotal(createdShipmentCommand.getGrandTotal())
                .tax(createdShipmentCommand.getTax())
                .userId(createdShipmentCommand.getUserId())
                .firstName(createdShipmentCommand.getFirstName())
                .lastName(createdShipmentCommand.getLastName())
                .address(createdShipmentCommand.getAddress())
                .paymentId(createdShipmentCommand.getPaymentId())
                .shipmentStatus(createdShipmentCommand.getShipmentStatus()).build();
        AggregateLifecycle.apply(shipmentCreatedEvent);
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
}
