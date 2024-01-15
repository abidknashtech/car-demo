package com.nashtech.inventory.restapi;

import com.nashtech.inventory.query.FindProductsQuery;
import com.nashtech.inventory.query.ProductsSummary;
import org.axonframework.queryhandling.QueryGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.concurrent.CompletableFuture;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductsControllerTest {
    @Mock
    private QueryGateway queryGateway;

    @InjectMocks
    private ProductsController productsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetProducts() {
        String productId = "123";
        ProductsSummary expectedProductsSummary = new ProductsSummary(/* provide necessary data */);
        CompletableFuture<ProductsSummary> queryResult = CompletableFuture.completedFuture(expectedProductsSummary);

        when(queryGateway.query(new FindProductsQuery(productId), ProductsSummary.class))
                .thenReturn(queryResult);

        ProductsSummary actualProductsSummary = productsController.getProducts(productId);

        assertEquals(expectedProductsSummary, actualProductsSummary);
    }

}