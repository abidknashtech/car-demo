package com.nashtech.inventory.aggregate;


import com.nashtech.common.command.CancelProductReserveCommand;
import com.nashtech.common.command.ReserveProductCommand;
import com.nashtech.common.event.ProductReserveCancelledEvent;
import com.nashtech.common.event.ProductReserveFailedEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.inventory.command.CreateProductCommand;
import com.nashtech.inventory.events.ProductCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;



@Aggregate
@Slf4j
public class InventoryAggregate{

	@AggregateIdentifier
	private String productId;
	private String title;
	private Double basePrice;
	private Float tax;
	private Integer quantity;

	private String userId;
	private String orderId;

	public InventoryAggregate() {

	}

	@CommandHandler
	public InventoryAggregate(CreateProductCommand createProductCommand) {
	log.info("CreateProductCommand started with productId {}",createProductCommand.getProductId());
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
		log.info("ReserveProductCommand started with productId {}",reserveProductCommand.getProductId());
		if(quantity < reserveProductCommand.getQuantity()) {
			ProductReserveFailedEvent productFailedEvent = ProductReserveFailedEvent.builder()
					.productId(reserveProductCommand.getProductId())
					.userId(reserveProductCommand.getUserId())
					.orderId(reserveProductCommand.getOrderId())
					.reasonToFailed("Insufficient number of items in stock").build();
			AggregateLifecycle.apply(productFailedEvent);
			return;
		}

		ProductReservedEvent productReservedEvent = ProductReservedEvent.builder()
				.orderId(reserveProductCommand.getOrderId())
				.productId(reserveProductCommand.getProductId())
				.userId(reserveProductCommand.getUserId())
				.quantity(reserveProductCommand.getQuantity())
				.title(title)
				.baseAmount(basePrice)
				.tax(tax)
				.build();

		AggregateLifecycle.apply(productReservedEvent);

	}

	@CommandHandler
	public void handle(CancelProductReserveCommand cancelProductReservationCommand) {
		log.info("ProductReserveCancelledEvent started with productId {}",cancelProductReservationCommand.getProductId());
		ProductReserveCancelledEvent productReservationCancelledEvent = ProductReserveCancelledEvent.builder()
				.productId(cancelProductReservationCommand.getProductId())
				.userId(cancelProductReservationCommand.getUserId())
				.orderId(cancelProductReservationCommand.getOrderId())
				.quantity(cancelProductReservationCommand.getQuantity())
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
		this.basePrice = productCreatedEvent.getBasePrice();
		this.tax = productCreatedEvent.getTax();
		this.title = productCreatedEvent.getTitle();
		this.quantity = productCreatedEvent.getQuantity();
	}


	@EventSourcingHandler
	public void on(ProductReservedEvent productReservedEvent) {
		this.userId = productReservedEvent.getUserId();
		this.orderId = productReservedEvent.getOrderId();
		this.quantity -= productReservedEvent.getQuantity();
	}

}
