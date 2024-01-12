package com.nashtech.inventory.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.spring.pubsub.support.BasicAcknowledgeablePubsubMessage;
import com.nashtech.inventory.command.CreateProductCommand;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class ProductSubscriberServiceTest {

    @Mock
    private CommandGateway commandGateway;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private BasicAcknowledgeablePubsubMessage message;

    @InjectMocks
    private ProductSubscriberService productSubscriberService;

    @BeforeEach
    void setUp() {
        // Initialize mocks
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void messageReceiver_SuccessfulParsing() throws JsonProcessingException {
        // Arrange
        String payload = "{\"productId\":1,\"brand\":\"Brand\",\"model\":\"Model\",\"year\":2022,\"color\":\"Red\",\"mileage\":100,\"basePrice\":10000,\"quantity\":5,\"tax\":0.1}";
        ProductRequest productRequest = new ProductRequest();  // Assuming you have a default constructor for ProductRequest

        when(objectMapper.readValue(payload, ProductRequest.class)).thenReturn(productRequest);

        // Act
        productSubscriberService.messageReceiver(payload, message);

        // Assert
        verify(message).ack();

        // You can add more assertions based on your specific requirements
        verify(commandGateway).send(any(CreateProductCommand.class));
    }

    @Test
    void messageReceiver_FailedParsing() throws JsonProcessingException {
        // Arrange
        String payload = "invalid_payload";

        when(objectMapper.readValue(payload, ProductRequest.class)).thenThrow(new JsonProcessingException("Error") {});

        // Act
        productSubscriberService.messageReceiver(payload, message);

        // Assert
        verify(message, never()).ack();

        // You can add more assertions based on your specific requirements
        verify(commandGateway, never()).send(any(CreateProductCommand.class));
    }
}