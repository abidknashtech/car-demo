package com.knoldus.cloudfunction.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class VehicleTest {
    /**
     * Method under test: {@link Vehicle#canEqual(Object)}
     */
    @Test
    void testCanEqual() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        String string = "Other";

        // Act
        boolean actualCanEqualResult = vehicle.canEqual(string);

        // Assert
        assertFalse(actualCanEqualResult);
    }

    /**
     * Method under test: {@link Vehicle#canEqual(Object)}
     */
    @Test
    void testCanEqual2() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualCanEqualResult = vehicle.canEqual(vehicle2);

        // Assert
        assertTrue(actualCanEqualResult);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);
        Vehicle vehicle2 = null;

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals2() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);
        String string = "Different type to Vehicle";

        // Act
        boolean actualEqualsResult = vehicle.equals(string);

        // Assert
        assertNotEquals(vehicle, string);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals3() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Model");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals4() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(null);
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals5() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(2);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals6() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(null);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals7() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Model");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals8() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor(null);
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals9() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(null);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals10() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(0.5d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals11() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Brand");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals12() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel(null);
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals13() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(null);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals14() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(0.5d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals15() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(3);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals16() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(null);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals17() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(null);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals18() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(0.5d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals19() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(3);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Method under test: {@link Vehicle#equals(Object)}
     */
    @Test
    void testEquals20() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(null);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertNotEquals(vehicle, vehicle2);
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Vehicle#equals(Object)}
     *   <li>{@link Vehicle#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle);

        // Assert
        assertEquals(vehicle, vehicle);
        int expectedHashCodeResult = vehicle.hashCode();
        assertEquals(expectedHashCodeResult, vehicle.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Vehicle#equals(Object)}
     *   <li>{@link Vehicle#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode2() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertEquals(vehicle, vehicle2);
        int expectedHashCodeResult = vehicle.hashCode();
        assertEquals(expectedHashCodeResult, vehicle2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Vehicle#equals(Object)}
     *   <li>{@link Vehicle#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode3() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(null);
        vehicle.setCarId(1);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand(null);
        vehicle2.setCarId(1);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertEquals(vehicle, vehicle2);
        int expectedHashCodeResult = vehicle.hashCode();
        assertEquals(expectedHashCodeResult, vehicle2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>{@link Vehicle#equals(Object)}
     *   <li>{@link Vehicle#hashCode()}
     * </ul>
     */
    @Test
    void testEqualsAndHashCode4() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand("Brand");
        vehicle.setCarId(null);
        vehicle.setColor("Color");
        vehicle.setMileage(10.0d);
        vehicle.setModel("Model");
        vehicle.setPrice(10.0d);
        vehicle.setQuantity(1);
        vehicle.setTax(10.0d);
        vehicle.setYear(1);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setBrand("Brand");
        vehicle2.setCarId(null);
        vehicle2.setColor("Color");
        vehicle2.setMileage(10.0d);
        vehicle2.setModel("Model");
        vehicle2.setPrice(10.0d);
        vehicle2.setQuantity(1);
        vehicle2.setTax(10.0d);
        vehicle2.setYear(1);

        // Act
        boolean actualEqualsResult = vehicle.equals(vehicle2);

        // Assert
        assertEquals(vehicle, vehicle2);
        int expectedHashCodeResult = vehicle.hashCode();
        assertEquals(expectedHashCodeResult, vehicle2.hashCode());
    }

    /**
     * Methods under test:
     *
     * <ul>
     *   <li>default or parameterless constructor of {@link Vehicle}
     *   <li>{@link Vehicle#setBrand(String)}
     *   <li>{@link Vehicle#setCarId(Integer)}
     *   <li>{@link Vehicle#setColor(String)}
     *   <li>{@link Vehicle#setMileage(Double)}
     *   <li>{@link Vehicle#setModel(String)}
     *   <li>{@link Vehicle#setPrice(Double)}
     *   <li>{@link Vehicle#setQuantity(Integer)}
     *   <li>{@link Vehicle#setTax(Double)}
     *   <li>{@link Vehicle#setYear(Integer)}
     *   <li>{@link Vehicle#toString()}
     *   <li>{@link Vehicle#getBrand()}
     *   <li>{@link Vehicle#getCarId()}
     *   <li>{@link Vehicle#getColor()}
     *   <li>{@link Vehicle#getMileage()}
     *   <li>{@link Vehicle#getModel()}
     *   <li>{@link Vehicle#getPrice()}
     *   <li>{@link Vehicle#getQuantity()}
     *   <li>{@link Vehicle#getTax()}
     *   <li>{@link Vehicle#getYear()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Vehicle actualVehicle = new Vehicle();
        String brand = "Brand";
        actualVehicle.setBrand(brand);
        int carId = 1;
        actualVehicle.setCarId(carId);
        String color = "Color";
        actualVehicle.setColor(color);
        double mileage = 10.0d;
        actualVehicle.setMileage(mileage);
        String model = "Model";
        actualVehicle.setModel(model);
        double price = 10.0d;
        actualVehicle.setPrice(price);
        int quantity = 1;
        actualVehicle.setQuantity(quantity);
        double tax = 10.0d;
        actualVehicle.setTax(tax);
        int year = 1;
        actualVehicle.setYear(year);
        String actualToStringResult = actualVehicle.toString();
        String actualBrand = actualVehicle.getBrand();
        Integer actualCarId = actualVehicle.getCarId();
        String actualColor = actualVehicle.getColor();
        Double actualMileage = actualVehicle.getMileage();
        String actualModel = actualVehicle.getModel();
        Double actualPrice = actualVehicle.getPrice();
        Integer actualQuantity = actualVehicle.getQuantity();
        Double actualTax = actualVehicle.getTax();
        Integer actualYear = actualVehicle.getYear();

        // Assert that nothing has changed
        assertEquals("Brand", actualBrand);
        assertEquals("Color", actualColor);
        assertEquals("Model", actualModel);
        assertEquals("Vehicle(carId=1, model=Model, brand=Brand, year=1, color=Color, mileage=10.0, price=10.0, quantity=1,"
                + " tax=10.0)", actualToStringResult);
        assertEquals(1, actualCarId.intValue());
        assertEquals(1, actualQuantity.intValue());
        assertEquals(1, actualYear.intValue());
        assertEquals(10.0d, actualMileage.doubleValue());
        assertEquals(10.0d, actualPrice.doubleValue());
        assertEquals(10.0d, actualTax.doubleValue());
    }
}
