package com.knoldus.cloudfunction;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.knoldus.cloudfunction.model.Vehicle;
import io.cloudevents.CloudEvent;
import io.cloudevents.CloudEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PubSubDataHandlerTest {

    @Mock
    private Firestore mockFirestore;

    @Mock
    private ApiFuture<WriteResult> mockApiFuture;

    @Mock
    private DocumentReference mockDocumentReference;

    @Mock
    private CollectionReference mockCollectionReference;

    private PubSubDataHandler pubSubDataHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        pubSubDataHandler = new PubSubDataHandler(mockFirestore);

        when(mockFirestore.collection("Car")).thenReturn(mockCollectionReference);
        when(mockCollectionReference.document()).thenReturn(mockDocumentReference);
        when(mockDocumentReference.set(any(Vehicle.class))).thenReturn(mockApiFuture);
    }

    @Test
    void constructorTest() {
        assertNotNull(pubSubDataHandler, "PubSubDataHandler should be initialized");
    }


    /**
     * Method under test: {@link PubSubDataHandler#accept(CloudEvent)}
     */
    @Test
    void testAccept() throws IOException {
        // Arrange
        PubSubDataHandler pubSubDataHandler = new PubSubDataHandler(mock(Firestore.class));
        CloudEventData cloudEventData = mock(CloudEventData.class);
        when(cloudEventData.toBytes()).thenThrow(new RuntimeException("foo"));
        CloudEvent event = mock(CloudEvent.class);
        when(event.getData()).thenReturn(cloudEventData);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> pubSubDataHandler.accept(event));
        verify(event).getData();
        verify(cloudEventData).toBytes();
    }

    @Test
    void transformPriceTest() {
        double priceInDollars = 10;
        double conversionRate = 74.85;
        double expectedPriceInRupees = priceInDollars * conversionRate;
        double actualPriceInRupees = pubSubDataHandler.transformPrice(priceInDollars, conversionRate);
        assertEquals(expectedPriceInRupees, actualPriceInRupees,
                "Price should be correctly transformed from dollars to rupees");
    }

    @Test
    void transformMileageTest() {
        double mileageInMiles = 10;
        double expectedMileageInKilometers = mileageInMiles * PubSubDataHandler.MILEAGE_CONVERSION_RATE_;
        double actualMileageInKilometers = pubSubDataHandler.transformMileage(mileageInMiles);
        assertEquals(expectedMileageInKilometers, actualMileageInKilometers,
                "Mileage should be correctly transformed from miles to kilometers");
    }

    @Test
    void saveDataToFirestoreSuccessTest() throws ExecutionException, InterruptedException {
        Vehicle vehicleData = mock(Vehicle.class);

        when(mockApiFuture.get()).thenReturn(null); // Simulate successful Firestore operation

        assertDoesNotThrow(() -> pubSubDataHandler.saveDataToFirestore(vehicleData),
                "Saving data to Firestore should not throw an exception");

        verify(mockFirestore.collection("Car")).document();
        verify(mockDocumentReference).set(vehicleData);
    }

    @Test
    void fetchConversionRateFromAPITest() throws Exception {
        String toCurrency = "INR";
        double expectedRate = 82.76;
        double delta = 0.01; // Tolerance for the comparison

        // Mocking HttpURLConnection for API response simulation
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
        when(mockConnection.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        InputStream mockInputStream = new ByteArrayInputStream(
                ("{\"data\": {\"" + toCurrency + "\": {\"value\": " + expectedRate + "}}}").getBytes());
        when(mockConnection.getInputStream()).thenReturn(mockInputStream);

        double actualRate = pubSubDataHandler.fetchConversionRateFromAPI(toCurrency);

        assertEquals(expectedRate, actualRate, delta, "The fetched currency conversion rate should be correct");
    }

}
