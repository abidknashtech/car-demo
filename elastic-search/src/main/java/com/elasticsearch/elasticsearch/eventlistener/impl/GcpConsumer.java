package com.elasticsearch.elasticsearch.eventlistener.impl;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.elasticsearch.elasticsearch.eventlistener.GcpCloudConsumer;
import com.elasticsearch.elasticsearch.service.CarService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.pubsub.v1.PubsubMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@Profile("gcp")
public class GcpConsumer extends GcpCloudConsumer {

    @Value("${pubSub.subscriptionId}")
    private String subscription;

    @Autowired
    private PubSubTemplate pubSubTemplate;

    @Autowired
    private CarService service;

    private final ObjectMapper objectMapper;

    public GcpConsumer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String subscription() {
        return this.subscription;
    }

    @Override
    protected void consume(BasicAcknowledgeablePubsubMessage basicAcknowledgeablePubsubMessage) {
        PubsubMessage message = basicAcknowledgeablePubsubMessage.getPubsubMessage();
        log.info("Message received from {}", basicAcknowledgeablePubsubMessage.getProjectSubscriptionName());
        try {
            log.info("Message: {}", message.getData().toStringUtf8());
            processMessage(basicAcknowledgeablePubsubMessage);
        } catch (Exception ex) {
            log.error("Error Occurred while receiving pubsub message:::::", ex);
        }
        basicAcknowledgeablePubsubMessage.ack();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void subscribe() {
        log.info("Subscribing {} to {} ", this.getClass().getSimpleName(), this.subscription());
        pubSubTemplate.subscribe(this.subscription(), this.messageConsumer());
    }

    private void processMessage(BasicAcknowledgeablePubsubMessage message) {
        CarEntity[] gcHubMessageArray;
        String eventMsgString = "";
        try {
            eventMsgString = message.getPubsubMessage().getData().toStringUtf8();
            gcHubMessageArray = objectMapper.readValue(eventMsgString, CarEntity[].class);
            CarEntity[] gcpElasticCarEntityList = gcHubMessageArray;
            for (CarEntity carEntity : gcpElasticCarEntityList) {
                service.saveCarEntity(carEntity);
            }
        } catch (IllegalArgumentException | JsonProcessingException e) {
            log.error("Json exception in parsing message: {}", eventMsgString, e);
            message.ack();
        }
    }
}