package com.nashtech.order.events.handler;
import com.nashtech.order.events.OrderCreatedEvent;
import com.nashtech.order.repository.entity.OrderLookup;
import com.nashtech.order.repository.OrderLookupRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.stereotype.Component;

@Component
@ProcessingGroup("order-group")
public class OrderLookupEventsHandler {
	
	private final OrderLookupRepository orderLookupRepository;
	
	public OrderLookupEventsHandler(OrderLookupRepository orderLookupRepository) {
		this.orderLookupRepository = orderLookupRepository;
	}

	@EventHandler
	public void on(OrderCreatedEvent event) {
		OrderLookup orderLookup = new OrderLookup(event.getOrderId());
		orderLookupRepository.save(orderLookup);
	}

	@ExceptionHandler
	public void handle(Exception exception) throws Exception {
		throw exception;
	}
}