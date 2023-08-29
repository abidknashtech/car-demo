package com.nashtech.order.saga;

import com.nashtech.common.command.CompleteOrderCommand;
import com.nashtech.common.command.ProcessPaymentCommand;
import com.nashtech.common.command.ShipOrderCommand;
import com.nashtech.common.event.OrderCompletedEvent;
import com.nashtech.common.event.OrderShippedEvent;
import com.nashtech.common.event.PaymentProcessedEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.common.model.PaymentDetails;
import com.nashtech.common.model.User;
import com.nashtech.order.api.response.OrderSummary;
import com.nashtech.order.commands.CancelOrderCommand;
import com.nashtech.common.command.ReserveProductCommand;
import com.nashtech.order.events.OrderCreatedEvent;
import com.nashtech.order.query.FindOrderQuery;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.queryhandling.QueryGateway;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import com.nashtech.common.utils.OrderStatus;

import java.util.UUID;


@Saga
@Slf4j
public class OrderSaga {
    @Autowired
    private transient CommandGateway commandGateway;

    @Autowired
    private transient QueryGateway queryGateway;

    @Autowired
    private transient QueryUpdateEmitter queryUpdateEmitter;

    @StartSaga
    @SagaEventHandler(associationProperty = "orderId")
    private void handle(OrderCreatedEvent orderCreatedEvent) {

        log.info("OrderCreatedEvent in Saga for Order Id : {}", orderCreatedEvent.getCarId());

        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .orderId(orderCreatedEvent.getOrderId())
                .carId(orderCreatedEvent.getCarId())
                .quantity(orderCreatedEvent.getQuantity())
                .userId(orderCreatedEvent.getUserId())
                .build();

        commandGateway.send(reserveProductCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // Start a compensating transaction
                cancelOrderCommand(orderCreatedEvent.getOrderId(),commandResultMessage.exceptionResult().getMessage(),
                        OrderStatus.ORDER_REJECTED);
            }

        });
    }

    @SagaEventHandler(associationProperty="orderId")
    public void handle(ProductReservedEvent productReservedEvent) {
        // Process user payment
        log.info("ProductReserveddEvent is called for carId: "+ productReservedEvent.getCarId() +
                " and orderId: " + productReservedEvent.getOrderId());

        PaymentDetails paymentDetails = PaymentDetails.builder()
                .bank("SBI")
                .cardNumber("0900987654435443")
                .validUntilYear(2028)
                .validUntilMonth(6)
                .cvv(334)
                .build();

        User userOrderDetails = User.builder()
                .firstName("Abid")
                .lastName("Khan")
                .address("Aligarh")
                .userId(productReservedEvent.getUserId())
                .paymentDetails(paymentDetails)
                .build();
        log.info("Successfully fetched user payment details for user " + userOrderDetails.getFirstName());

        ProcessPaymentCommand processPaymentCommand = ProcessPaymentCommand.builder()
                .orderId(productReservedEvent.getOrderId())
                .paymentDetails(userOrderDetails)
                .paymentId(UUID.randomUUID().toString())
                .build();

        String result = null;
        try {
            result = commandGateway.sendAndWait(processPaymentCommand);
        } catch(Exception ex) {
            log.error(ex.getMessage());
            // Start compensating transaction
            cancelOrderCommand(productReservedEvent.getOrderId(),ex.getMessage(), OrderStatus.ORDER_REJECTED);
        }

        if(result == null) {
            log.info("The ProcessPaymentCommand resulted in NULL. Initiating a compensating transaction");
            // Start compensating transaction
            cancelOrderCommand(productReservedEvent.getOrderId(),
                    "Could not process user payment with provided payment details",OrderStatus.ORDER_REJECTED);
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handle(PaymentProcessedEvent event) {
        log.info("PaymentProcessedEvent in Saga for Order Id : {}", event.getOrderId());
        try {
            ShipOrderCommand shipOrderCommand
                    = ShipOrderCommand
                    .builder()
                    .shipmentId(UUID.randomUUID().toString())
                    .orderId(event.getOrderId())
                    .build();
            commandGateway.send(shipOrderCommand);
        } catch (Exception e) {
            log.error(e.getMessage());
            // Start the compensating transaction
        }
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderShippedEvent event) {

        log.info("OrderShippedEvent in Saga for Order Id : {}",
                event.getOrderId());

        CompleteOrderCommand completeOrderCommand
                = CompleteOrderCommand.builder()
                .orderId(event.getOrderId())
                .orderStatus(event.getShipmentStatus())
                .build();

        commandGateway.send(completeOrderCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCompletedEvent event) {
        log.info("Order is approved. Order Saga is complete for orderId: " + event.getOrderId());
        queryUpdateEmitter.emit(FindOrderQuery.class, query -> true,
                new OrderSummary(event.getOrderId(), event.getOrderStatus()));
    }

    private void cancelOrderCommand(String orderId,String reason, OrderStatus orderStatus) {
        CancelOrderCommand cancelOrderCommand
                = new CancelOrderCommand(orderId, orderStatus,reason);
        commandGateway.send(cancelOrderCommand);
    }
}
