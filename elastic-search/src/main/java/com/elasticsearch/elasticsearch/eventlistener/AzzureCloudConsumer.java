package com.elasticsearch.elasticsearch.eventlistener;

import org.springframework.context.annotation.Profile;

@Profile("azure")
public interface AzzureCloudConsumer {

    void consumeEvent(String event);
}
