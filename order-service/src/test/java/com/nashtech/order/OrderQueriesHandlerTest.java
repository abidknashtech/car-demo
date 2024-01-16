package com.nashtech.order;

import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.query.FindOrdersByUserQuery;
import com.nashtech.order.query.OrderQueriesHandler;
import com.nashtech.order.repository.OrderRepository;
import com.nashtech.order.repository.entity.OrderEntity;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderQueriesHandlerTest {

    private static final String ORDER_ID1 = UUID.randomUUID().toString();
    private static final String ORDER_ID2 = UUID.randomUUID().toString();
    private static final String PRODUCT_ID = UUID.randomUUID().toString();
    private static final String USER_ID = "1652";

    private final OrderRepository orderRepository = mock(OrderRepository.class);

    private OrderQueriesHandler queriesHandler;

    @Before
    public void setUp() {
        queriesHandler = new OrderQueriesHandler(orderRepository);
    }

    @Test
    public void testOrderCreatedEventHandler() {
        FindOrdersByUserQuery findOrdersByUserQuery = new FindOrdersByUserQuery(USER_ID);

        List<OrderEntity> orderEntityList = new ArrayList<>();
        OrderEntity order1 = new OrderEntity(ORDER_ID1, USER_ID, PRODUCT_ID,
                null, null, new Date(), OrderStatus.ORDER_APPROVED.toString());
        orderEntityList.add(order1);
        OrderEntity order2= new OrderEntity(ORDER_ID2, USER_ID, PRODUCT_ID,
                null, null, new Date(), OrderStatus.ORDER_APPROVED.toString());
        orderEntityList.add(order2);
        when(orderRepository.findByUserId(USER_ID)).thenReturn(orderEntityList);

        List<OrderEntity> orders = queriesHandler.findOrders(findOrdersByUserQuery);

        Assert.assertEquals(2,orders.size());
        Assert.assertEquals(ORDER_ID1,orders.get(0).getOrderId());
        Assert.assertEquals(ORDER_ID2,orders.get(1).getOrderId());
    }
}
