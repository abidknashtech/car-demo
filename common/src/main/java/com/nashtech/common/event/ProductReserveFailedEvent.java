package com.nashtech.common.event;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class ProductReserveFailedEvent {
    String orderId;
    String userId;
    String reasonToFailed;
    Map<Boolean,String> inStock;
}
