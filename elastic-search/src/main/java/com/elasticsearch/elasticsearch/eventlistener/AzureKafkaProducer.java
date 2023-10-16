/*
package com.elasticsearch.elasticsearch.eventlistener;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
@Profile("azure")
public class AzureKafkaProducer {

    private final KafkaTemplate<String, CarEntity> kafkaTemplate;
    @Value(("${topic.producer}"))
    private String topicName;

    public void send(CarEntity message){
        this.kafkaTemplate.send(topicName,message);
        log.info("Published the message [{}] to the kafka queue: [{}]",message,topicName);
    }
}
*/
