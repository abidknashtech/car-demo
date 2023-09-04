package com.nashtech.shipment.aggregate;

import com.nashtech.common.commands.CancelShipmentCommand;
import com.nashtech.common.commands.CreatedShipmentCommand;
import com.nashtech.common.event.ShipmentCancelEvent;
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
    private ShipmentStatus shipmentStatus;

    public ShipmentAggregate() {
    }

    @CommandHandler
    public ShipmentAggregate(CreatedShipmentCommand createdShipmentCommand) {
        LOGGER.info("CreatedShipmentCommand is called for shipmentId: " + createdShipmentCommand.getShipmentId());
        ShipmentCreatedEvent shipmentCreatedEvent = ShipmentCreatedEvent.builder()
                    .shipmentId(createdShipmentCommand.getShipmentId())
                    .orderId(createdShipmentCommand.getOrderId())
                    .shipmentStatus(createdShipmentCommand.getShipmentStatus()).build();
        AggregateLifecycle.apply(shipmentCreatedEvent);
    }

    @CommandHandler
    public void handle(CancelShipmentCommand cancelShipmentCommand) {
        LOGGER.info("CancelShipmentCommand is called for shipmentId: " + cancelShipmentCommand.getShipmentId());
        ShipmentCancelEvent cancelShipmentEvent = ShipmentCancelEvent.builder()
                .shipmentId(cancelShipmentCommand.getShipmentId())
                .orderId(cancelShipmentCommand.getOrderId())
                .shipmentStatus(cancelShipmentCommand.getShipmentStatus()).build();
        AggregateLifecycle.apply(cancelShipmentEvent);
    }

    @EventSourcingHandler
    public  void on(ShipmentCreatedEvent shipmentCreatedEvent) {
        this.shipmentId = shipmentCreatedEvent.getShipmentId();
        this.orderId = shipmentCreatedEvent.getOrderId();
        this.shipmentStatus = shipmentCreatedEvent.getShipmentStatus();
    }

    @EventSourcingHandler
    public void on(ShipmentCancelEvent cancelShipmentEvent) {
        this.shipmentId = cancelShipmentEvent.getShipmentId();
        this.orderId = cancelShipmentEvent.getOrderId();
        this.shipmentStatus = cancelShipmentEvent.getShipmentStatus();
    }
}
