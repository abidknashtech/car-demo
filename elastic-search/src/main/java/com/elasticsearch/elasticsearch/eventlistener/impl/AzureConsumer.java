package com.elasticsearch.elasticsearch.eventlistener.impl;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.elasticsearch.elasticsearch.eventlistener.CloudConsumer;
import com.elasticsearch.elasticsearch.service.CarService;
import com.elasticsearch.elasticsearch.util.CarMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("azure")
public class AzureConsumer implements CloudConsumer {
    @Autowired
    private CarService service;

    @KafkaListener(topics = "${topic.producer}")
    public void consumeEvent(String event) {
        log.info("Received message from kafka queue: {}", event);
        CarEntity carEntity = CarMapper.mapStringToEntity(event);
        service.saveCarEntity(carEntity);
        log.info(carEntity.toString());
    }
}
