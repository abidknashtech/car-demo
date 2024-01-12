package com.nashtech.inventory.command.interceptors;

import com.nashtech.inventory.command.CreateProductCommand;
import com.nashtech.inventory.repository.ProductLookupEntity;
import com.nashtech.inventory.repository.ProductLookupRepository;
import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.commandhandling.GenericCommandMessage;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateProductCommandInterceptorTest {
    @Test
    public void test_interceptCreateProductCommand() {
        // Arrange
        ProductLookupRepository productLookupRepository = mock(ProductLookupRepository.class);
        CreateProductCommandInterceptor interceptor = new CreateProductCommandInterceptor(productLookupRepository);
        List<CommandMessage<?>> messages = new ArrayList<>();
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId("123")
                .brand("Brand")
                .model("Model")
                .year(2021)
                .color("Color")
                .mileage(1000.0)
                .basePrice(10000.0)
                .quantity(1)
                .tax(0.1f)
                .build();
        CommandMessage<CreateProductCommand> commandMessage = new GenericCommandMessage<>(createProductCommand);

        // Act
        BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> result = interceptor.handle(messages);
        CommandMessage<?> interceptedCommand = result.apply(0, commandMessage);

        // Assert
        assertEquals(commandMessage, interceptedCommand);
    }
    @Test
    public void test_throwExceptionIfProductExists() {
        // Arrange
        ProductLookupRepository productLookupRepository = mock(ProductLookupRepository.class);
        CreateProductCommandInterceptor interceptor = new CreateProductCommandInterceptor(productLookupRepository);
        List<CommandMessage<?>> messages = new ArrayList<>();
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId("123")
                .brand("Brand")
                .model("Model")
                .year(2021)
                .color("Color")
                .mileage(1000.0)
                .basePrice(10000.0)
                .quantity(1)
                .tax(0.1f)
                .build();
        CommandMessage<CreateProductCommand> commandMessage = new GenericCommandMessage<>(createProductCommand);
        ProductLookupEntity existingProduct = new ProductLookupEntity();
        when(productLookupRepository.findByProductId(createProductCommand.getProductId())).thenReturn(existingProduct);

        // Act
        BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> result = interceptor.handle(messages);
        assertThrows(IllegalStateException.class, () -> result.apply(0, commandMessage));
    }
    @Test
    public void test_returnCommandIfProductDoesNotExist() {
        // Arrange
        ProductLookupRepository productLookupRepository = mock(ProductLookupRepository.class);
        CreateProductCommandInterceptor interceptor = new CreateProductCommandInterceptor(productLookupRepository);
        List<CommandMessage<?>> messages = new ArrayList<>();
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId("123")
                .brand("Brand")
                .model("Model")
                .year(2021)
                .color("Color")
                .mileage(1000.0)
                .basePrice(10000.0)
                .quantity(1)
                .tax(0.1f)
                .build();
        CommandMessage<CreateProductCommand> commandMessage = new GenericCommandMessage<>(createProductCommand);
        when(productLookupRepository.findByProductId(createProductCommand.getProductId())).thenReturn(null);

        // Act
        BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> result = interceptor.handle(messages);
        CommandMessage<?> interceptedCommand = result.apply(0, commandMessage);

        // Assert
        assertEquals(commandMessage, interceptedCommand);
    }
    @Test
    public void test_noCreateProductCommandInMessages() {
        // Arrange
        ProductLookupRepository productLookupRepository = mock(ProductLookupRepository.class);
        CreateProductCommandInterceptor interceptor = new CreateProductCommandInterceptor(productLookupRepository);
        List<CommandMessage<?>> messages = new ArrayList<>();
        CommandMessage<?> commandMessage = new GenericCommandMessage<>(new Object());

        // Act
        BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> result = interceptor.handle(messages);
        CommandMessage<?> interceptedCommand = result.apply(0, commandMessage);

        // Assert
        assertEquals(commandMessage, interceptedCommand);
    }
    @Test
    public void test_productLookupRepositoryReturnsNull() {
        // Arrange
        ProductLookupRepository productLookupRepository = mock(ProductLookupRepository.class);
        CreateProductCommandInterceptor interceptor = new CreateProductCommandInterceptor(productLookupRepository);
        List<CommandMessage<?>> messages = new ArrayList<>();
        CreateProductCommand createProductCommand = CreateProductCommand.builder()
                .productId("123")
                .brand("Brand")
                .model("Model")
                .year(2021)
                .color("Color")
                .mileage(1000.0)
                .basePrice(10000.0)
                .quantity(1)
                .tax(0.1f)
                .build();
        CommandMessage<CreateProductCommand> commandMessage = new GenericCommandMessage<>(createProductCommand);
        when(productLookupRepository.findByProductId(createProductCommand.getProductId())).thenReturn(null);

        // Act
        BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> result = interceptor.handle(messages);
        CommandMessage<?> interceptedCommand = result.apply(0, commandMessage);

        // Assert
        assertEquals(commandMessage, interceptedCommand);
    }
}
