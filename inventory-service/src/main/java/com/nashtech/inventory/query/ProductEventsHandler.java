package com.nashtech.inventory.query;


import com.nashtech.common.event.ProductReserveCancelledEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.inventory.core.data.Product;
import com.nashtech.inventory.core.data.ProductsRepository;
import com.nashtech.inventory.core.data.ProductsSold;
import com.nashtech.inventory.core.data.ProductsSoldRepository;
import com.nashtech.inventory.core.events.ProductCreatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.ResetHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
@ProcessingGroup("product-group")
public class ProductEventsHandler {

	private final ProductsRepository productsRepository;
	private final ProductsSoldRepository productsSoldRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductEventsHandler.class);

	public ProductEventsHandler(ProductsRepository productsRepository, ProductsSoldRepository productsSoldRepository) {
		this.productsRepository = productsRepository;
		this.productsSoldRepository = productsSoldRepository;
	}

	@ExceptionHandler(resultType=Exception.class)
	public void handle(Exception exception) throws Exception {
		throw exception;
	}

	@ExceptionHandler(resultType=IllegalArgumentException.class)
	public void handle(IllegalArgumentException exception) {

	}


	@EventHandler
	public void on(ProductCreatedEvent event) {

		Product productEntity = new Product();
		BeanUtils.copyProperties(event, productEntity);

		try {
			productsRepository.save(productEntity);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
		}

	}

	@EventHandler
	public void on(ProductReservedEvent productReservedEvent) {
		Product productEntity = productsRepository.findByProductId(productReservedEvent.getProductId());

		LOGGER.debug("ProductReservedEvent: Current product quantity " + productEntity.getQuantity());

		productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());

		productsRepository.save(productEntity);

		LOGGER.debug("ProductReservedEvent: New product quantity " + productEntity.getQuantity());

		// Update the quantity in the productsSoldRepository
		ProductsSold soldProduct = productsSoldRepository.findByProductId(productReservedEvent.getProductId());
		if (soldProduct == null) {
			// If the product hasn't been sold before, create a new entry
			soldProduct= new ProductsSold();
			soldProduct.setProductId(productReservedEvent.getProductId());
			soldProduct.setQuantity(productReservedEvent.getQuantity());
		} else {
			// Increment the existing sold count
			soldProduct.setQuantity(soldProduct.getQuantity() + productReservedEvent.getQuantity());
		}
		// Save the updated sold product entity to the productsSoldRepository
		productsSoldRepository.save(soldProduct);

		LOGGER.info("ProductReservedEvent is called for productId:" + productReservedEvent.getProductId() +
				" and orderId: " + productReservedEvent.getOrderId());
	}

	@EventHandler
	public void on(ProductReserveCancelledEvent productReservationCancelledEvent) {
		Product currentlyStoredProduct =  productsRepository.findByProductId(productReservationCancelledEvent.getOrderId());

		LOGGER.debug("ProductReservationCancelledEvent: Current product quantity "
				+ currentlyStoredProduct.getQuantity() );

		int newQuantity = currentlyStoredProduct.getQuantity() + productReservationCancelledEvent.getQuantity();
		currentlyStoredProduct.setQuantity(newQuantity);

		productsRepository.save(currentlyStoredProduct);

		LOGGER.debug("ProductReservationCancelledEvent: New product quantity "
				+ currentlyStoredProduct.getQuantity() );

		ProductsSold currentlyStoredSoldProduct = productsSoldRepository.findByProductId(productReservationCancelledEvent.getOrderId());
		LOGGER.debug("ProductReservationCancelledEvent: Current soldProduct quantity "
				+ currentlyStoredSoldProduct.getQuantity() );
		int newSoldQuantity = currentlyStoredSoldProduct.getQuantity() - productReservationCancelledEvent.getQuantity();
		currentlyStoredProduct.setQuantity(newSoldQuantity);
		productsSoldRepository.save(currentlyStoredSoldProduct);

		LOGGER.debug("ProductReservationCancelledEvent: New soldProduct quantity "
				+ currentlyStoredSoldProduct.getQuantity() );
	}

	@ResetHandler
	public void reset() {
		productsRepository.deleteAll();
	}

}
