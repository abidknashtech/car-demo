package com.nashtech.shipment.handler;

import com.nashtech.common.event.ShipmentCreatedEvent;
import com.nashtech.shipment.entity.ShipmentEntity;
import com.nashtech.shipment.repository.ShipmentRepository;
import com.nashtech.shipment.service.PubSubPublisherService;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ShipmentEventHandler {
    private final ShipmentRepository shipmentRepository;
    private final PubSubPublisherService pubSubPublisherService;

    public ShipmentEventHandler(ShipmentRepository shipmentRepository, PubSubPublisherService pubSubPublisherService) {
        this.shipmentRepository = shipmentRepository;
        this.pubSubPublisherService = pubSubPublisherService;
    }

    @EventHandler
    public void on(ShipmentCreatedEvent shipmentCreatedEvent) {
        log.info("ShipmentCreatedEvent is called for shipmentId: {}", shipmentCreatedEvent.getShipmentId());
        ShipmentEntity shipmentEntity = new ShipmentEntity(
                shipmentCreatedEvent.getShipmentId(),
                shipmentCreatedEvent.getOrderId(),
                shipmentCreatedEvent.getProductId(),
                shipmentCreatedEvent.getQuantity(),
                shipmentCreatedEvent.getPrice(),
                shipmentCreatedEvent.getSubTotal(),
                shipmentCreatedEvent.getGrandTotal(),
                shipmentCreatedEvent.getTax(),
                shipmentCreatedEvent.getUserId(),
                shipmentCreatedEvent.getFirstName(),
                shipmentCreatedEvent.getLastName(),
                shipmentCreatedEvent.getAddress(),
                shipmentCreatedEvent.getPaymentId()
        );
        log.info("Sending Shipment details to pub sub topic");
        pubSubPublisherService.messagePublisher(shipmentEntity);
        log.info("Saving Shipment details to database");
        shipmentRepository.save(shipmentEntity);
    }
}
