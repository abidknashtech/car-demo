//package com.nashtech.inventory.handler;
//
//import com.nashtech.common.event.ProductReserveCancelledEvent;
//import com.nashtech.common.event.ProductReservedEvent;
//import com.nashtech.inventory.events.ProductCreatedEvent;
//import com.nashtech.inventory.repository.ProductEntity;
//import com.nashtech.inventory.repository.ProductsRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//class ProductEventsHandlerTest {
//
//    @Mock
//    private ProductsRepository productsRepository;
//
//    @InjectMocks
//    private ProductEventsHandler productEventsHandler;
//
//    @Test
//    public void testOnProductCreatedEvent() {
//        ProductCreatedEvent event = new ProductCreatedEvent();
//        // Set up your event object
//
//        productEventsHandler.on(event);
//
//        // Verify that productsRepository.save() is called with the correct argument
//        verify(productsRepository).save(any(ProductEntity.class));
//    }
//
//    @Test
//    public void testOnProductReservedEvent() {
//        ProductReservedEvent event = new ProductReservedEvent();
//        // Set up your event object
//
//        // Mock repository response
//        when(productsRepository.findByProductId(any())).thenReturn(new ProductEntity());
//
//        productEventsHandler.on(event);
//
//        // Verify that productsRepository.save() is called with the correct argument
//        verify(productsRepository).save(any(ProductEntity.class));
//    }
//
//    @Test
//    public void testOnProductReserveCancelledEvent() {
//        ProductReserveCancelledEvent event = new ProductReserveCancelledEvent();
//        // Set up your event object
//
//        // Mock repository response
//        when(productsRepository.findByProductId(any())).thenReturn(new ProductEntity());
//
//        productEventsHandler.on(event);
//
//        // Verify that productsRepository.save() is called with the correct argument
//        verify(productsRepository).save(any(ProductEntity.class));
//    }
//}