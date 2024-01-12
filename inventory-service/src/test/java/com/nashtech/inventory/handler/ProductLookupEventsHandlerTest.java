package com.nashtech.inventory.handler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nashtech.inventory.events.ProductCreatedEvent;
import com.nashtech.inventory.repository.ProductLookupEntity;
import com.nashtech.inventory.repository.ProductLookupRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductLookupEventsHandler.class})
@ExtendWith(SpringExtension.class)
class ProductLookupEventsHandlerTest {
    @Autowired
    private ProductLookupEventsHandler productLookupEventsHandler;

    @MockBean
    private ProductLookupRepository productLookupRepository;

    /**
     * Method under test: {@link ProductLookupEventsHandler#on(ProductCreatedEvent)}
     */
    @Test
    void testOn() {
        // Arrange
        when(productLookupRepository.save(Mockito.<ProductLookupEntity>any())).thenReturn(new ProductLookupEntity());
        ProductCreatedEvent event = ProductCreatedEvent.builder()
                .basePrice(10.0d)
                .brand("Brand")
                .color("Color")
                .mileage(10.0d)
                .model("Model")
                .productId("42")
                .quantity(1)
                .tax(10.0f)
                .year(1)
                .build();

        // Act
        productLookupEventsHandler.on(event);

        // Assert
        verify(productLookupRepository).save(Mockito.<ProductLookupEntity>any());
    }
}
