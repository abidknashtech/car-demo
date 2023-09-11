package com.nashtech.inventory.command;


import com.nashtech.common.command.ReserveProductCommand;
import com.nashtech.common.event.ProductReserveCancelledEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.inventory.core.events.ProductCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;



@Aggregate
public class InventoryAggregate{

	@AggregateIdentifier
	private String productId;
	private String title;
	private Double price;
	private Integer quantity;

	public InventoryAggregate() {

	}

	@CommandHandler
	public InventoryAggregate(CreateProductCommand createProductCommand) {

		if(createProductCommand.getTitle() == null
				|| createProductCommand.getTitle().isBlank()) {
			throw new IllegalArgumentException("Title cannot be empty");
		}

		ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();

		BeanUtils.copyProperties(createProductCommand, productCreatedEvent);

		AggregateLifecycle.apply(productCreatedEvent);
	}

	@CommandHandler
	public void handle(ReserveProductCommand reserveProductCommand) {

		if(quantity < reserveProductCommand.getQuantity()) {
			ProductReserveCancelledEvent productReserveCancelledEvent = ProductReserveCancelledEvent.builder()
					.orderId(reserveProductCommand.getOrderId())
					.reasonToFailed("Insufficient number of items in stock").build();
		}

		ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
				.orderId(reserveProductCommand.getOrderId())
				.productId(reserveProductCommand.getProductId())
				.quantity(reserveProductCommand.getQuantity())
				.userId(reserveProductCommand.getUserId())
				.build();

		AggregateLifecycle.apply(productReservedEvent);

	}

	@CommandHandler
	public void handle(ProductReserveCancelledEvent cancelProductReservationCommand) {

		ProductReserveCancelledEvent productReservationCancelledEvent =
				ProductReserveCancelledEvent.builder()
				.orderId(cancelProductReservationCommand.getOrderId())
				.build();

		AggregateLifecycle.apply(productReservationCancelledEvent);

	}


	@EventSourcingHandler
	public void on(ProductReserveCancelledEvent productReservationCancelledEvent) {
		this.quantity += productReservationCancelledEvent.getQuantity();
	}


	@EventSourcingHandler
	public void on(ProductCreatedEvent productCreatedEvent) {
		this.productId = productCreatedEvent.getProductId();
		this.price = productCreatedEvent.getPrice();
		this.title = productCreatedEvent.getTitle();
		this.quantity = productCreatedEvent.getQuantity();
	}


	@EventSourcingHandler
	public void on(ProductReservedEvent productReservedEvent) {
		this.quantity -= productReservedEvent.getQuantity();
	}



}
