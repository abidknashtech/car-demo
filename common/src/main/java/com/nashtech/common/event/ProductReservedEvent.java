package com.nashtech.common.event;

import com.nashtech.common.model.LineItem;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class ProductReservedEvent {
    String orderId;
    String userId;
    Map<String, LineItem> orderLines;
}