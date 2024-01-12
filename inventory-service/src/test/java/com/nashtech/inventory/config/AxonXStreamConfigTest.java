package com.nashtech.inventory.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider;
import com.thoughtworks.xstream.mapper.CachingMapper;
import org.junit.jupiter.api.Test;

class AxonXStreamConfigTest {
    /**
     * Method under test: {@link AxonXStreamConfig#xStream()}
     */
    @Test
    void testXStream() {

        // Arrange and Act
        XStream actualXStreamResult = (new AxonXStreamConfig()).xStream();

        // Assert
        assertTrue(actualXStreamResult.getReflectionProvider() instanceof SunUnsafeReflectionProvider);
        assertTrue(actualXStreamResult.getMapper() instanceof CachingMapper);
        assertNotNull(actualXStreamResult.getClassLoader());
        assertNotNull(actualXStreamResult.getClassLoaderReference().getReference());
    }
}
