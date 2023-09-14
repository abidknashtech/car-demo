package com.nashtech.car.cart.model;

import com.nashtech.car.cart.audit.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class CartItem extends Auditable<String> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productId;
    private int quantity;
    private String userId;

}
