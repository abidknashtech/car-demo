package com.nashtech.shipment.aggregate;

import com.nashtech.common.commands.CancelShipmentCommand;
import com.nashtech.common.commands.CreatedShipmentCommand;
import com.nashtech.common.event.ShipmentCancelEvent;
import com.nashtech.common.event.ShipmentCreatedEvent;
import com.nashtech.common.model.ShipmentStatus;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class ShipmentAggregate {

    @AggregateIdentifier
    private String shipmentId;
    private String orderId;
    private ShipmentStatus shipmentStatus;

    public ShipmentAggregate() {
    }

    @CommandHandler
    public ShipmentAggregate(CreatedShipmentCommand createdShipmentCommand) {
        ShipmentCreatedEvent shipmentCreatedEvent = new ShipmentCreatedEvent();
        BeanUtils.copyProperties(createdShipmentCommand, shipmentCreatedEvent);
        AggregateLifecycle.apply(shipmentCreatedEvent);
    }

    @CommandHandler
    public void handle(CancelShipmentCommand cancelShipmentCommand) {
        ShipmentCancelEvent cancelShipmentEvent = new ShipmentCancelEvent();
        BeanUtils.copyProperties(cancelShipmentCommand, cancelShipmentEvent);
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
