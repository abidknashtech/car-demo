package com.nashtech.inventory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.nashtech.inventory.command.interceptors.CreateProductCommandInterceptor;
import com.nashtech.inventory.exception.ProductsServiceEventsErrorHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

class InventoryApplicationTest {
    @Test
    public void test_create_product_command_interceptor_registered_successfully() {
        ApplicationContext context = mock(ApplicationContext.class);
        CommandBus commandBus = mock(CommandBus.class);
        CreateProductCommandInterceptor interceptor = mock(CreateProductCommandInterceptor.class);

        when(context.getBean(CreateProductCommandInterceptor.class)).thenReturn(interceptor);

        InventoryApplication inventoryApplication = new InventoryApplication();

        inventoryApplication.registerCreateProductCommandInterceptor(context, commandBus);

        verify(commandBus).registerDispatchInterceptor(interceptor);
    }
    @Test
    public void test_products_service_events_error_handler_configured_successfully() {
        EventProcessingConfigurer config = mock(EventProcessingConfigurer.class);
        ProductsServiceEventsErrorHandler errorHandler = mock(ProductsServiceEventsErrorHandler.class);

        when(config.registerListenerInvocationErrorHandler(eq("product-group"), any())).thenReturn(config);

        InventoryApplication inventoryApplication = new InventoryApplication();

        inventoryApplication.configure(config);

        verify(config).registerListenerInvocationErrorHandler(eq("product-group"), any());
    }

    @Test
    public void test_create_product_command_interceptor_is_null() {
        ApplicationContext context = mock(ApplicationContext.class);
        CommandBus commandBus = mock(CommandBus.class);

        InventoryApplication inventoryApplication = new InventoryApplication();

        inventoryApplication.registerCreateProductCommandInterceptor(context, commandBus);
    }
}
