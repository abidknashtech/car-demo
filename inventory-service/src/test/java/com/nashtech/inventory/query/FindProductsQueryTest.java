package com.nashtech.inventory.query;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FindProductsQueryTest {
    @Test
    public void test_valid_productId() {
        String productId = "123";
        FindProductsQuery query = new FindProductsQuery(productId);
        assertNotNull(query);
        assertEquals(productId, query.getProductId());
    }

    @Test
    public void test_null_productId() {
        FindProductsQuery query = new FindProductsQuery(null);
        assertNotNull(query);
        assertNull(query.getProductId());
    }
    @Test
    public void test_empty_productId() {
        String productId = "";
        FindProductsQuery query = new FindProductsQuery(productId);
        assertNotNull(query);
        assertEquals(productId, query.getProductId());
    }
}