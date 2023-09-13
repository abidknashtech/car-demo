package com.nashtech.order.query;

import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.repository.OrderRepository;
import com.nashtech.order.restapi.response.OrderSummary;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
public class OrderQueriesHandler {

    OrderRepository ordersRepository;

    public OrderQueriesHandler(OrderRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @QueryHandler
    public OrderSummary findOrder(FindOrderQuery findOrderQuery) {
        return OrderSummary.builder()
                .orderId(findOrderQuery.getOrderId())
                .orderStatus(OrderStatus.ORDER_PLACED.toString())
                .build();
    }

}
