package com.nashtech.inventory.config;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.cloud.spring.core.GcpProjectIdProvider;
import com.google.cloud.spring.pubsub.core.PubSubConfiguration;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.core.publisher.PubSubPublisherTemplate;
import com.google.cloud.spring.pubsub.core.subscriber.PubSubSubscriberTemplate;
import com.google.cloud.spring.pubsub.support.DefaultSubscriberFactory;
import com.google.cloud.spring.pubsub.support.PublisherFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PubSubConfig.class})
@ExtendWith(SpringExtension.class)
class PubSubConfigTest {
    @Autowired
    private PubSubConfig pubSubConfig;

    @MockBean
    private PubSubTemplate pubSubTemplate;

    /**
     * Method under test: {@link PubSubConfig#inputMessageChannel()}
     */
    @Test
    void testInputMessageChannel() {
        // Arrange, Act and Assert
        assertTrue(pubSubConfig.inputMessageChannel() instanceof PublishSubscribeChannel);
    }

    /**
     * Method under test:
     * {@link PubSubConfig#inboundChannelAdapter(MessageChannel, PubSubTemplate)}
     */
    @Test
    void testInboundChannelAdapter() {
        // Arrange
        MessageChannel messageChannel = mock(MessageChannel.class);
        GcpProjectIdProvider projectIdProvider = mock(GcpProjectIdProvider.class);
        when(projectIdProvider.getProjectId()).thenReturn("myproject");
        PubSubSubscriberTemplate pubSubSubscriberTemplate = new PubSubSubscriberTemplate(
                new DefaultSubscriberFactory(projectIdProvider, new PubSubConfiguration()));

        // Act
        pubSubConfig.inboundChannelAdapter(messageChannel,
                new PubSubTemplate(new PubSubPublisherTemplate(mock(PublisherFactory.class)), pubSubSubscriberTemplate));

        // Assert
        verify(projectIdProvider).getProjectId();
    }
}
