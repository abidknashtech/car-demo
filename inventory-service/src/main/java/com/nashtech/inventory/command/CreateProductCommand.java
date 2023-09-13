package com.nashtech.inventory.command;

import lombok.Builder;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Value
public class CreateProductCommand {
	@TargetAggregateIdentifier
	String productId;
	String title;
	Double basePrice;
	Float tax;
	Integer quantity;

}
