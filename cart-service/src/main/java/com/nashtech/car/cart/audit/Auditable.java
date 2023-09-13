package com.nashtech.car.cart.audit;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

    @CreatedBy
    protected T createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date createdDate;

    @LastModifiedBy
    protected T lastModifiedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date lastModifiedDate;

}
