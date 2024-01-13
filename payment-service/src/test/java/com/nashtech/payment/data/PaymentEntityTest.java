package com.nashtech.payment.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PaymentEntityTest {
    @Test
    void testPaymentEntityCreation() {
        // Given
        String paymentId = "123";
        String orderId = "456";
        String productId = "789";
        Integer quantity = 2;
        Double basePrice = 50.0;
        Double subTotal = 100.0;
        Double total = 110.0;
        Float tax = 5.0f;
        Float totalTax = 5.5f;

        // When
        PaymentEntity paymentEntity = PaymentEntity.builder()
                .paymentId(paymentId)
                .orderId(orderId)
                .productId(productId)
                .quantity(quantity)
                .basePrice(basePrice)
                .subTotal(subTotal)
                .total(total)
                .tax(tax)
                .totalTax(totalTax)
                .build();

        // Then
        assertNotNull(paymentEntity);
        assertEquals(paymentId, paymentEntity.getPaymentId());
        assertEquals(orderId, paymentEntity.getOrderId());
        assertEquals(productId, paymentEntity.getProductId());
        assertEquals(quantity, paymentEntity.getQuantity());
        assertEquals(basePrice, paymentEntity.getBasePrice());
        assertEquals(subTotal, paymentEntity.getSubTotal());
        assertEquals(total, paymentEntity.getTotal());
        assertEquals(tax, paymentEntity.getTax());
        assertEquals(totalTax, paymentEntity.getTotalTax());
    }
}
