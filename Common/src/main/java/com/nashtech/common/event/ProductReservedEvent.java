package com.nashtech.common.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductReservedEvent {
	private final String carId;
	private final int quantity;
	private final String orderId;
	private final String userId;
}