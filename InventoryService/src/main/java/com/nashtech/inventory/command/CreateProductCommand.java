package com.nashtech.inventory.command;

import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;

@Builder
@Value
public class CreateProductCommand {

	@TargetAggregateIdentifier
	String productId;
	String title;
	Double price;
	Integer quantity;

}
