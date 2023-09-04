package com.nashtech.shipment.handler;

import com.nashtech.common.event.ShipmentCancelEvent;
import com.nashtech.common.event.ShipmentCreatedEvent;
import com.nashtech.shipment.entity.ShipmentEntity;
import com.nashtech.shipment.repository.ShipmentRepository;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ShipmentEventHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(ShipmentEventHandler.class);
    private final ShipmentRepository shipmentRepository;

    public ShipmentEventHandler(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @EventHandler
    public void on(ShipmentCreatedEvent shipmentCreatedEvent) {
        LOGGER.info("ShipmentCreatedEvent is called for shipmentId: " + shipmentCreatedEvent.getShipmentId());
        ShipmentEntity shipmentEntity = new ShipmentEntity(
                shipmentCreatedEvent.getShipmentId(),
                shipmentCreatedEvent.getOrderId(),
                shipmentCreatedEvent.getShipmentStatus()
        );
        shipmentRepository.save(shipmentEntity);
    }

    @EventHandler
    public void on(ShipmentCancelEvent shipmentCancelEvent) {
        ShipmentEntity shipmentEntity = shipmentRepository.findByShipmentId(shipmentCancelEvent.getShipmentId());
        if (shipmentEntity == null) {
            LOGGER.info("No record found for shipmentId: " + shipmentCancelEvent.getShipmentId());
        } else {
            LOGGER.info("ShipmentCancelEvent is called for shipmentId: " + shipmentCancelEvent.getShipmentId());
            shipmentEntity.setShipmentStatus(shipmentCancelEvent.getShipmentStatus());
            shipmentRepository.save(shipmentEntity);
        }
    }
}
