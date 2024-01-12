package com.nashtech.payment.aggregate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.nashtech.common.event.PaymentApprovedEvent;
import com.nashtech.common.event.PaymentCancelledEvent;
import com.nashtech.common.model.User;
import org.junit.jupiter.api.Test;

class PaymentAggregateTest {
    /**
     * Method under test: {@link PaymentAggregate#PaymentAggregate()}
     */
    @Test
    void testNewPaymentAggregate() {
        // Arrange and Act
        PaymentAggregate actualPaymentAggregate = new PaymentAggregate();

        // Assert
        assertNull(actualPaymentAggregate.getBasePrice());
        assertNull(actualPaymentAggregate.getQuantity());
        assertNull(actualPaymentAggregate.getOrderId());
        assertNull(actualPaymentAggregate.getPaymentId());
        assertNull(actualPaymentAggregate.getProductId());
    }

    /**
     * Method under test: {@link PaymentAggregate#on(PaymentApprovedEvent)}
     */
    @Test
    void testOn() {
        // Arrange
        PaymentAggregate paymentAggregate = new PaymentAggregate();
        PaymentApprovedEvent.PaymentApprovedEventBuilder totalTaxResult = PaymentApprovedEvent.builder()
                .basePrice(10.0d)
                .brand("Brand")
                .orderId("42")
                .paymentId("42")
                .productId("42")
                .quantity(1)
                .subTotal(10.0d)
                .tax(10.0f)
                .total(10.0d)
                .totalTax(10.0f);
        User user = User.builder().emailId("42").firstName("Jane").lastName("Doe").mobileNumber("42").userId("42").build();
        PaymentApprovedEvent paymentApprovedEvent = totalTaxResult.user(user).build();

        // Act
        paymentAggregate.on(paymentApprovedEvent);

        // Assert
        assertEquals("42", paymentAggregate.getOrderId());
        assertEquals("42", paymentAggregate.getPaymentId());
        assertEquals("42", paymentAggregate.getProductId());
        assertEquals(1, paymentAggregate.getQuantity().intValue());
    }

    /**
     * Method under test: {@link PaymentAggregate#on(PaymentCancelledEvent)}
     */
    @Test
    void testOn2() {
        // Arrange
        PaymentAggregate paymentAggregate = new PaymentAggregate();
        PaymentCancelledEvent paymentApprovedEvent = PaymentCancelledEvent.builder()
                .orderId("42")
                .paymentId("42")
                .productId("42")
                .quantity(1)
                .reasonToFailed("Just cause")
                .userId("42")
                .build();

        // Act
        paymentAggregate.on(paymentApprovedEvent);

        // Assert
        assertEquals("42", paymentAggregate.getOrderId());
        assertEquals("42", paymentAggregate.getPaymentId());
        assertEquals("42", paymentAggregate.getProductId());
        assertEquals(1, paymentAggregate.getQuantity().intValue());
    }
}
