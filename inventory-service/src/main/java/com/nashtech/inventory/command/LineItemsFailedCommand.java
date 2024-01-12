package com.nashtech.inventory.command;


import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LineItemsFailedCommand {
    String productId;
    String reasonToFailed;
}
