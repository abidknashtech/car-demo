package com.elasticsearch.elasticsearch.eventlistener;

public interface CloudConsumer {

    void consumeEvent(String event);
}
