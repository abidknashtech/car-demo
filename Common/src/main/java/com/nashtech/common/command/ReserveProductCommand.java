package com.nashtech.order.commands;

import com.nashtech.common.model.User;
import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
@Builder
public class ReserveProductCommand {

	@TargetAggregateIdentifier
    String carId;
	int quantity;
	String orderId;
	User user;
	
	
}
