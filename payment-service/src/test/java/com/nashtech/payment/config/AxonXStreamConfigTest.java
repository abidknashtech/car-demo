package com.nashtech.payment.config;

import com.thoughtworks.xstream.XStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AxonXStreamConfigTest {
    @Test
    void testXStreamBeanCreation() {
        AxonXStreamConfig axonXStreamConfig = new AxonXStreamConfig();
        XStream xStream = axonXStreamConfig.xStream();

        assertNotNull(xStream);
    }
}
