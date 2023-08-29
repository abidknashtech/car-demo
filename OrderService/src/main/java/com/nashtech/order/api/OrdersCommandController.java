package com.nashtech.order.api;


import java.util.UUID;

import com.nashtech.order.api.request.OrderCreateRequest;
import com.nashtech.order.api.response.OrderSummary;
import com.nashtech.order.commands.CreateOrderCommand;
import com.nashtech.order.query.FindOrderQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.nashtech.common.utils.OrderStatus;

import javax.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrdersCommandController {

	private final CommandGateway commandGateway;
	private final QueryGateway queryGateway;

	@Autowired
	public OrdersCommandController(CommandGateway commandGateway, QueryGateway queryGateway) {
		this.commandGateway = commandGateway;
		this.queryGateway = queryGateway;
	}

	@PostMapping("/create")
	public OrderSummary createOrder(@Valid @RequestBody OrderCreateRequest order) {

		String orderId = UUID.randomUUID().toString();

		CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
				.carId(order.getCarId()).userId(order.getUserId())
				.quantity(order.getQuantity()).orderId(orderId)
				.price(order.getPrice())
				.orderStatus(OrderStatus.ORDER_CREATED)
				.paymentDetails(order.getPaymentDetails())
				.build();

        try (SubscriptionQueryResult<OrderSummary, OrderSummary> queryResult = queryGateway.subscriptionQuery(
                new FindOrderQuery(orderId), ResponseTypes.instanceOf(OrderSummary.class),
                ResponseTypes.instanceOf(OrderSummary.class))) {
            commandGateway.sendAndWait(createOrderCommand);
            return queryResult.updates().blockFirst();
        }

	}

}