package com.nashtech.order.api;


import com.nashtech.order.api.request.OrderCreateRequest;
import com.nashtech.order.api.response.OrderSummary;
import com.nashtech.order.commands.CreateOrderCommand;
import com.nashtech.order.query.FindOrderQuery;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.SubscriptionQueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

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
    public OrderSummary createOrder(@Valid @RequestBody OrderCreateRequest orderRequest) {
        String orderId = UUID.randomUUID().toString();

        CreateOrderCommand createOrderCommand = CreateOrderCommand.builder()
                .carId(orderRequest.getCarId()).userId(orderRequest.getUserId())
                .quantity(orderRequest.getQuantity()).orderId(orderId)
                .price(orderRequest.getPrice())
                .build();

        try (SubscriptionQueryResult<OrderSummary, OrderSummary> queryResult = queryGateway.subscriptionQuery(
                FindOrderQuery.builder().orderId(orderId).build(), ResponseTypes.instanceOf(OrderSummary.class),
                ResponseTypes.instanceOf(OrderSummary.class))) {
            commandGateway.send(createOrderCommand);
            return queryResult.updates().blockFirst();
        }
    }

}