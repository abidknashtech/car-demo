package com.nashtech.inventory.events;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class ProductCreatedEventTest {
  /**
   * Methods under test:
   *
   * <ul>
   *   <li>
   * {@link ProductCreatedEvent#ProductCreatedEvent(String, String, String, Integer, String, Double, Double, Integer, Float)}
   *   <li>{@link ProductCreatedEvent#toString()}
   *   <li>{@link ProductCreatedEvent#getBasePrice()}
   *   <li>{@link ProductCreatedEvent#getBrand()}
   *   <li>{@link ProductCreatedEvent#getColor()}
   *   <li>{@link ProductCreatedEvent#getMileage()}
   *   <li>{@link ProductCreatedEvent#getModel()}
   *   <li>{@link ProductCreatedEvent#getProductId()}
   *   <li>{@link ProductCreatedEvent#getQuantity()}
   *   <li>{@link ProductCreatedEvent#getTax()}
   *   <li>{@link ProductCreatedEvent#getYear()}
   * </ul>
   */
  @Test
  void testGettersAndSetters() {
    // Arrange and Act
    ProductCreatedEvent actualProductCreatedEvent = new ProductCreatedEvent("42", "Brand", "Model", 1, "Color", 10.0d,
        10.0d, 1, 10.0f);
    String actualToStringResult = actualProductCreatedEvent.toString();
    Double actualBasePrice = actualProductCreatedEvent.getBasePrice();
    String actualBrand = actualProductCreatedEvent.getBrand();
    String actualColor = actualProductCreatedEvent.getColor();
    Double actualMileage = actualProductCreatedEvent.getMileage();
    String actualModel = actualProductCreatedEvent.getModel();
    String actualProductId = actualProductCreatedEvent.getProductId();
    Integer actualQuantity = actualProductCreatedEvent.getQuantity();
    Float actualTax = actualProductCreatedEvent.getTax();
    Integer actualYear = actualProductCreatedEvent.getYear();

    // Assert
    assertEquals("42", actualProductId);
    assertEquals("Brand", actualBrand);
    assertEquals("Color", actualColor);
    assertEquals("Model", actualModel);
    assertEquals("ProductCreatedEvent(productId=42, brand=Brand, model=Model, year=1, color=Color, mileage=10.0,"
        + " basePrice=10.0, quantity=1, tax=10.0)", actualToStringResult);
    assertEquals(1, actualQuantity.intValue());
    assertEquals(1, actualYear.intValue());
    assertEquals(10.0d, actualBasePrice.doubleValue());
    assertEquals(10.0d, actualMileage.doubleValue());
    assertEquals(10.0f, actualTax.floatValue());
  }
}
