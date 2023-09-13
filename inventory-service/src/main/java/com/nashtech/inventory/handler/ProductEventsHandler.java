package com.nashtech.inventory.handler;


import com.nashtech.common.event.ProductReserveCancelledEvent;
import com.nashtech.common.event.ProductReservedEvent;
import com.nashtech.inventory.events.ProductCreatedEvent;
import com.nashtech.inventory.repository.Product;
import com.nashtech.inventory.repository.ProductsRepository;
import com.nashtech.inventory.repository.ProductsSold;
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
        Product productEntity = new Product();
        BeanUtils.copyProperties(event, productEntity);
        productsRepository.save(productEntity);
    }

    @EventHandler
    public void on(ProductReservedEvent productReservedEvent) {
        log.info("ProductReservedEvent: Remaining product stock {}", productReservedEvent.getQuantity());
        Product product = productsRepository.findByProductId(productReservedEvent.getProductId());
        product.setQuantity(product.getQuantity() - productReservedEvent.getQuantity());
        productsRepository.save(product);

        ProductsSold soldProduct = new ProductsSold(product.getProductId(), productReservedEvent.getOrderId(),
                productReservedEvent.getUserId(), productReservedEvent.getQuantity(),productReservedEvent.getTitle(),
                productReservedEvent.getBaseAmount(),productReservedEvent.getTax());
        productsSoldRepository.save(soldProduct);
    }

    @EventHandler
    public void on(ProductReserveCancelledEvent productReservationCancelledEvent) {
        log.info("ProductReservationCancelledEvent: Reversing product {} quantity {}",
                productReservationCancelledEvent.getQuantity(), productReservationCancelledEvent.getProductId());

        Product currentlyStoredProduct = productsRepository.findByProductId(productReservationCancelledEvent.getOrderId());
        currentlyStoredProduct.setQuantity(currentlyStoredProduct.getQuantity() +
                productReservationCancelledEvent.getQuantity());

        productsRepository.save(currentlyStoredProduct);

        log.info("Stable product {} quantity {}", productReservationCancelledEvent.getProductId(),
				currentlyStoredProduct.getQuantity());

        ProductsSold currentlyStoredSoldProduct =
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
