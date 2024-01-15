package com.nashtech.inventory.config;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.reflection.SunUnsafeReflectionProvider;
import com.thoughtworks.xstream.mapper.CachingMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AxonXStreamConfigTest {
    @Test
    void testXStream() {

        XStream actualXStreamResult = (new AxonXStreamConfig()).xStream();

        assertInstanceOf(SunUnsafeReflectionProvider.class, actualXStreamResult.getReflectionProvider());
        assertInstanceOf(CachingMapper.class, actualXStreamResult.getMapper());
        assertNotNull(actualXStreamResult.getClassLoader());
        assertNotNull(actualXStreamResult.getClassLoaderReference().getReference());
    }
}
