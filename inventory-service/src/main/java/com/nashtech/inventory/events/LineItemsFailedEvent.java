package com.nashtech.inventory.events;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LineItemsFailedEvent {
    String productId;
    String reasonToFailed;
}
