package com.nashtech.order.query;

import com.nashtech.order.api.response.OrderSummary;
import com.nashtech.order.repository.OrderRepository;
import com.nashtech.order.repository.entity.Order;
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
        Order order = ordersRepository.findByOrderId(findOrderQuery.getOrderId());
        return OrderSummary.builder()
                .orderId(findOrderQuery.getOrderId())
                .orderStatus(order.getOrderStatus())
                .reason(order.getReasonToRejectedOrder())
                .build();
    }

}
