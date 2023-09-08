package com.nashtech.common.event;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ProductFailedEvent  {
    String orderId;
    String reasonToFailed;
    Integer quantity;
}
