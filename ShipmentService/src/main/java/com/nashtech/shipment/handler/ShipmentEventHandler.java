package com.nashtech.shipment.handler;

import com.nashtech.common.event.ShipmentCancelledEvent;
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
                shipmentCreatedEvent.getProductId(),
                shipmentCreatedEvent.getQuantity(),
                shipmentCreatedEvent.getPrice(),
                shipmentCreatedEvent.getUserId(),
                shipmentCreatedEvent.getPaymentId(),
                shipmentCreatedEvent.getShipmentStatus()
        );
        shipmentRepository.save(shipmentEntity);
    }

    @EventHandler
    public void on(ShipmentCancelledEvent shipmentCancelledEvent) {
        ShipmentEntity shipmentEntity = shipmentRepository.findByOrderId(shipmentCancelledEvent.getOrderId());
        if (shipmentEntity == null) {
            LOGGER.info("No record found for orderId: {}", shipmentCancelledEvent.getOrderId());
        } else {
            LOGGER.info("ShipmentCancelEvent is called for shipmentId: {}", shipmentCancelledEvent.getShipmentId());
            shipmentEntity.setShipmentStatus(shipmentCancelledEvent.getShipmentStatus());
            shipmentRepository.save(shipmentEntity);
        }
    }
}
