package com.nashtech.payment.data;

import com.nashtech.common.model.PaymentDetails;
import com.nashtech.common.model.PaymentStatus;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payments")
public class Payment {

    @Id
    private String  paymentId;
    private String orderId;
    private String productId;
    private String quantity;
    private Double baseAmount;
    private Double subTotal;
    private Double grantTotal;
    private Float tax;
    private PaymentStatus paymentStatus;

}
