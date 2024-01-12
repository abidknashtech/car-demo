package com.nashtech.inventory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nashtech.inventory.command.interceptors.CreateProductCommandInterceptor;
import com.nashtech.inventory.exception.ProductsServiceEventsErrorHandler;
import com.nashtech.inventory.repository.ProductLookupRepository;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.test.utils.RecordingCommandBus;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class InventoryApplicationTest {
    @Test
    public void test_create_product_command_interceptor_registered_successfully() {
        // Arrange
        ApplicationContext context = mock(ApplicationContext.class);
        CommandBus commandBus = mock(CommandBus.class);
        CreateProductCommandInterceptor interceptor = mock(CreateProductCommandInterceptor.class);

        when(context.getBean(CreateProductCommandInterceptor.class)).thenReturn(interceptor);

        InventoryApplication inventoryApplication = new InventoryApplication();

        // Act
        inventoryApplication.registerCreateProductCommandInterceptor(context, commandBus);

        // Assert
        verify(commandBus).registerDispatchInterceptor(interceptor);
    }
    @Test
    public void test_products_service_events_error_handler_configured_successfully() {
        // Arrange
        EventProcessingConfigurer config = mock(EventProcessingConfigurer.class);
        ProductsServiceEventsErrorHandler errorHandler = mock(ProductsServiceEventsErrorHandler.class);

        when(config.registerListenerInvocationErrorHandler(eq("product-group"), any())).thenReturn(config);

        InventoryApplication inventoryApplication = new InventoryApplication();

        // Act
        inventoryApplication.configure(config);

        // Assert
        verify(config).registerListenerInvocationErrorHandler(eq("product-group"), any());
    }

    @Test
    public void test_create_product_command_interceptor_is_null() {
        // Arrange
        ApplicationContext context = mock(ApplicationContext.class);
        CommandBus commandBus = mock(CommandBus.class);

        InventoryApplication inventoryApplication = new InventoryApplication();

        // Act
        inventoryApplication.registerCreateProductCommandInterceptor(context, commandBus);

        // Assert
        // No exceptions are thrown
    }
}
