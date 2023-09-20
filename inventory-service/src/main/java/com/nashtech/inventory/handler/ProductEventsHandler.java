package com.nashtech.inventory.handler;


import com.nashtech.common.event.ProductReserveCancelledEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.inventory.events.ProductCreatedEvent;
import com.nashtech.inventory.repository.ProductEntity;
import com.nashtech.inventory.repository.ProductsRepository;
import com.nashtech.inventory.repository.ProductsSoldEntity;
import com.nashtech.inventory.repository.ProductsSoldRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
@ProcessingGroup("product-group")
@Slf4j
public class ProductEventsHandler {

    private final ProductsRepository productsRepository;
    private final ProductsSoldRepository productsSoldRepository;

    public ProductEventsHandler(ProductsRepository productsRepository, ProductsSoldRepository productsSoldRepository) {
        this.productsRepository = productsRepository;
        this.productsSoldRepository = productsSoldRepository;
    }

    @EventHandler
    public void on(ProductCreatedEvent event) {
        ProductEntity productEntity = new ProductEntity();
        BeanUtils.copyProperties(event, productEntity);
        productsRepository.save(productEntity);
    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        log.info("ProductReservedEvent: Request quantity {}", productReservedEvent.getQuantity());
        ProductEntity productEntity = productsRepository.findByProductId(productReservedEvent.getProductId());
        productEntity.setQuantity(productEntity.getQuantity() - productReservedEvent.getQuantity());
        productsRepository.save(productEntity);

        ProductsSoldEntity soldProduct = new ProductsSoldEntity(productEntity.getProductId(), productReservedEvent.getOrderId(),
                productReservedEvent.getUserId(), productReservedEvent.getQuantity(),productReservedEvent.getSubTotal(),
                productReservedEvent.getTotal(),productReservedEvent.getTax());
        productsSoldRepository.save(soldProduct);
    }

    @EventHandler
    public void on(ProductReserveCancelledEvent productReservationCancelledEvent) {
        log.info("ProductReservationCancelledEvent: Reversing product {} quantity {}",
                productReservationCancelledEvent.getQuantity(), productReservationCancelledEvent.getProductId());

        ProductEntity currentlyStoredProductEntity = productsRepository.findByProductId(productReservationCancelledEvent.getOrderId());
        currentlyStoredProductEntity.setQuantity(currentlyStoredProductEntity.getQuantity() +
                productReservationCancelledEvent.getQuantity());

        productsRepository.save(currentlyStoredProductEntity);

        ProductsSoldEntity currentlyStoredSoldProduct =
                productsSoldRepository.findByProductIdAndOrderId(productReservationCancelledEvent.getProductId(),
                        productReservationCancelledEvent.getOrderId());
        if (currentlyStoredSoldProduct != null) {
            productsSoldRepository.delete(currentlyStoredSoldProduct);
        }
    }

    @ExceptionHandler(resultType = Exception.class)
    public void handle(Exception exception) throws Exception {
        throw exception;
    }

}
