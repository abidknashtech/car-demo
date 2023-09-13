package com.nashtech.order.saga;

import com.nashtech.common.command.ApproveOrderCommand;
import com.nashtech.common.command.CancelPaymentCommand;
import com.nashtech.common.command.CancelProductReserveCommand;
import com.nashtech.common.command.ProcessPaymentCommand;
import com.nashtech.common.command.ReserveProductCommand;
import com.nashtech.common.command.ShipmentOrderCommand;
import com.nashtech.common.event.OrderShippedEvent;
import com.nashtech.common.event.PaymentApprovedEvent;
import com.nashtech.common.event.PaymentCancelledEvent;
import com.nashtech.common.event.ProductReserveCancelledEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.common.event.ShipmentCancelledEvent;
import com.nashtech.common.model.PaymentDetails;
import com.nashtech.common.model.User;
import com.nashtech.common.utils.OderFailure;
import com.nashtech.common.utils.OrderStatus;
import com.nashtech.order.api.response.OrderSummary;
import com.nashtech.order.commands.RejectOrderCommand;
import com.nashtech.order.events.OrderApprovedEvent;
import com.nashtech.order.events.OrderCancelledEvent;
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
        log.info("Order Saga started for Order Id : {}", orderCreatedEvent.getOrderId());

        ReserveProductCommand reserveProductCommand = ReserveProductCommand.builder()
                .productId(orderCreatedEvent.getCarId())
                .orderId(orderCreatedEvent.getOrderId())
                .basePrice(orderCreatedEvent.getPrice())
                .quantity(orderCreatedEvent.getQuantity())
                .userId(orderCreatedEvent.getUserId())
                .build();

        commandGateway.send(reserveProductCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // Start a compensating transaction
                handleCompensatingTransaction(commandResultMessage.exceptionResult().getMessage(),
                        reserveProductCommand.getOrderId(), OderFailure.INVENTORY_SERVICE_NOT_AVAILABLE.toString());
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(ProductReservedEvent productReservedEvent) {
        // Process user payment
        log.info(String.format("ProductReservedEvent is called for productId: %s and orderId: %s ",
                productReservedEvent.getProductId(), productReservedEvent.getOrderId()));

        //Hard coded User and payment details
        PaymentDetails paymentDetails = PaymentDetails.builder()
                .bank("SBI")
                .cardNumber("0900987654435443")
                .validUntilYear(2028)
                .validUntilMonth(6)
                .cvv(334)
                .build();

        ProcessPaymentCommand processPaymentCommand = ProcessPaymentCommand.builder()
                .paymentId(UUID.randomUUID().toString()) // payment Id generation
                .orderId(productReservedEvent.getOrderId())
                .productId(productReservedEvent.getProductId())
                .userDetails(addUser(productReservedEvent.getUserId()))
                .paymentDetails(paymentDetails)
                .price(productReservedEvent.getPrice())
                .quantity(productReservedEvent.getQuantity())
                .build();


        commandGateway.send(processPaymentCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // Start a compensating transaction
                handleCompensatingTransaction(commandResultMessage.exceptionResult().getMessage(),
                        processPaymentCommand.getOrderId(), OderFailure.PAYMENT_SERVICE_NOT_AVAILABLE.toString());
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handle(ProductReserveCancelledEvent productReserveCancelledEvent) {
        log.info("ProductReserveCancelledEvent is occurred for orderId : {}", productReserveCancelledEvent.getOrderId());
        // Start the compensating transaction
        orderRejectedCommand(productReserveCancelledEvent.getOrderId(), productReserveCancelledEvent.getReasonToFailed());
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handle(PaymentApprovedEvent paymentApprovedEvent) {
        // log.info(String.format("ProductReservedEvent is called for productId: %s and orderId: %s "
        log.info("PaymentProcessedEvent is called for paymentId : {}", paymentApprovedEvent.getPaymentId());
        ShipmentOrderCommand shipmentOrderCommand = ShipmentOrderCommand.builder()
                .shipmentId(UUID.randomUUID().toString()) //Shipment Id generation
                .user(addUser(paymentApprovedEvent.getUserId()))
                .orderId(paymentApprovedEvent.getOrderId())
                .productId(paymentApprovedEvent.getProductId())
                .price(paymentApprovedEvent.getPrice())
                .quantity(paymentApprovedEvent.getQuantity())
                .build();

        commandGateway.send(shipmentOrderCommand, (commandMessage, commandResultMessage) -> {
            if (commandResultMessage.isExceptional()) {
                // Start the compensating transaction
                String errorMessage = commandResultMessage.exceptionResult().getMessage();
                log.error("PaymentProcessedEvent unable to process shipment due to {}", errorMessage);
                handleCompensatingTransaction(errorMessage, shipmentOrderCommand.getOrderId(),
                        OderFailure.SHIPMENT_SERVICE_NOT_AVAILABLE.toString());
            }
        });
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handle(PaymentCancelledEvent paymentCancelledEvent) {
        log.info("Payment in not approved!");
        // Start a compensating transaction
        CancelProductReserveCommand cancelProductReserveCommand = CancelProductReserveCommand.builder()
                .orderId(paymentCancelledEvent.getOrderId())
                .productId(paymentCancelledEvent.getProductId())
                .quantity(paymentCancelledEvent.getQuantity())
                .userId(paymentCancelledEvent.getUserId())
                .reasonToFailed(paymentCancelledEvent.getReason())
                .build();
        commandGateway.send(cancelProductReserveCommand);
        orderRejectedCommand(paymentCancelledEvent.getOrderId(), paymentCancelledEvent.getReason());
    }

    @SagaEventHandler(associationProperty = "orderId")
    public void handle(OrderShippedEvent orderShippedEvent) {
        log.info("OrderShippedEvent started for order approval");
        ApproveOrderCommand approveOrderCommand = ApproveOrderCommand.builder()
                .orderId(orderShippedEvent.getOrderId())
                .orderStatus(OrderStatus.ORDER_APPROVED)
                .build();
        commandGateway.send(approveOrderCommand);
    }

    @SagaEventHandler(associationProperty = "orderId")
    private void handle(ShipmentCancelledEvent shipmentCancelledEvent) {
        log.info("Start a compensating transaction from Shipment");
        // Start a compensating transaction
        CancelPaymentCommand cancelPaymentCommand = CancelPaymentCommand.builder()
                .paymentId(shipmentCancelledEvent.getPaymentId())
                .orderId(shipmentCancelledEvent.getOrderId())
                .productId(shipmentCancelledEvent.getProductId())
                .userId(shipmentCancelledEvent.getUserId())
                .reasonToFailed(shipmentCancelledEvent.getReasonToFailed())
                .quantity(shipmentCancelledEvent.getQuantity())
                .price(shipmentCancelledEvent.getPrice())
                .build();
        commandGateway.send(cancelPaymentCommand);

        CancelProductReserveCommand cancelProductReserveCommand = CancelProductReserveCommand.builder()
                .productId(shipmentCancelledEvent.getProductId())
                .orderId(shipmentCancelledEvent.getOrderId())
                .userId(shipmentCancelledEvent.getUserId())
                .quantity(shipmentCancelledEvent.getQuantity())
                .reasonToFailed(shipmentCancelledEvent.getReasonToFailed())
                .build();
        commandGateway.send(cancelProductReserveCommand);

        orderRejectedCommand(shipmentCancelledEvent.getOrderId(), shipmentCancelledEvent.getReasonToFailed());
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void on(OrderApprovedEvent event) {
        log.info("Order is approved. Order Saga is complete for orderId: " + event.getOrderId());
        OrderSummary orderSummary = OrderSummary.builder()
                .orderId(event.getOrderId())
                .orderStatus(event.getOrderStatus().toString())
                .build();
        queryUpdateEmitter.emit(FindOrderQuery.class, query -> true, orderSummary);
    }

    @SagaEventHandler(associationProperty = "orderId")
    @EndSaga
    public void handle(OrderCancelledEvent event) {
        log.info("Order is cancelled for orderId: " + event.getOrderId());
        OrderSummary orderSummary = OrderSummary.builder()
                .orderId(event.getOrderId())
                .orderStatus(event.getOrderStatus().toString())
                .reason(event.getReason())
                .build();
        queryUpdateEmitter.emit(FindOrderQuery.class, query -> true, orderSummary);
    }

    private void orderRejectedCommand(String orderId, String reason) {
        RejectOrderCommand rejectOrderCommand = RejectOrderCommand.builder()
                .orderId(orderId)
                .orderStatus(OrderStatus.ORDER_REJECTED)
                .reasonToReject(reason)
                .build();
        commandGateway.send(rejectOrderCommand);
    }

    private void handleCompensatingTransaction(String errorMessage, String orderId, String reason) {
        log.error("The resulted is exception {} . Initiating a compensating transaction", errorMessage);
        if (errorMessage != null && errorMessage.contains("No Handler for command:")) {
            orderRejectedCommand(orderId, reason);
        } else {
            orderRejectedCommand(orderId, errorMessage);
        }
    }

    private User addUser(String userId) {
        return User.builder()
                .firstName("Abid")
                .lastName("Khan")
                .address("Noida")
                .userId(userId)
                .build();
    }

}
