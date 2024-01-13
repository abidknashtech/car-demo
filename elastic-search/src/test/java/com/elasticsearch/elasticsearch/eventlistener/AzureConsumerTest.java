package com.elasticsearch.elasticsearch.eventlistener;

import com.elasticsearch.elasticsearch.entity.CarEntity;
import com.elasticsearch.elasticsearch.eventlistener.impl.AzureConsumer;
import com.elasticsearch.elasticsearch.service.CarService;
import com.elasticsearch.elasticsearch.util.CarMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class AzureConsumerTest {

    @InjectMocks
    private AzureConsumer azureConsumer;

    @Mock
    private CarService carService;

    @Mock
    private CarMapper carMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testConsumeEvents() {
        String kafkaMessage = "{\"id\":\"1\",\"make\":\"Toyota\",\"model\":\"Camry\",\"year\":2022}";

        ConsumerRecord<String, String> consumerRecord = new ConsumerRecord<>(
                "test-topic",
                0,
                0,
                "key",
                kafkaMessage
        );

        azureConsumer.consumeEvents(consumerRecord);
        CarEntity expectedCarEntity = CarMapper.mapStringToEntity(kafkaMessage);
        Mockito.verify(carService, Mockito.times(1)).saveCarEntity(expectedCarEntity);
    }
    @Test
    public void test_logs_received_message() {
        AzureConsumer azureConsumer = new AzureConsumer();
        String event = "Test message";
        azureConsumer.consumeEvent(event);
    }

}