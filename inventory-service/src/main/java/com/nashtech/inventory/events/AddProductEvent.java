package com.nashtech.inventory.events;

import com.nashtech.common.model.LineItem;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AddProductEvent {
    String orderId;
    LineItem lineItem;
}
