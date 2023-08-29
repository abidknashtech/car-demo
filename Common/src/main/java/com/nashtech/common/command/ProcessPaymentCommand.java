package com.nashtech.common.command;

import com.nashtech.common.model.PaymentDetails;
import com.nashtech.common.model.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Builder
@Data
public class ProcessPaymentCommand {

	@TargetAggregateIdentifier
	private final String paymentId;
	private final String orderId;
	private final User paymentDetails;
	
}