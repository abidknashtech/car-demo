package com.nashtech.inventory.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.google.cloud.spring.pubsub.support.GcpPubSubHeaders;
import com.nashtech.inventory.command.CreateProductCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class ProductSubscriberService {

    private final CommandGateway commandGateway;
    private final ObjectMapper objectMapper;

    public ProductSubscriberService(CommandGateway commandGateway, ObjectMapper objectMapper) {
        this.commandGateway = commandGateway;
        this.objectMapper = objectMapper;
    }


    @ServiceActivator(inputChannel = "inputMessageChannel")
    public void messageReceiver(String payload,
                                @Header(GcpPubSubHeaders.ORIGINAL_MESSAGE) BasicAcknowledgeablePubsubMessage message) {
        log.info("Message arrived via an inbound channel adapter from sub-one! Payload: " + payload);
        ProductRequest productRequest;
        try {
            productRequest = objectMapper.readValue(payload, ProductRequest.class);
            message.ack();
        } catch (JsonProcessingException e) {
            log.error("Unable to parse payload {} due to error {} ", payload, e.getMessage());
            return;
        }

        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .basePrice(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .title(productRequest.getTitle())
                .tax(productRequest.getTax())
                .productId(UUID.randomUUID().toString()).build();
        commandGateway.send(createProductCommand);
    }

}
