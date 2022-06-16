package com.kulov.bank;

import io.dropwizard.kafka.deserializer.DeserializerFactory;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomDeserializerFactory extends DeserializerFactory {
    @Override
    public Class<? extends Deserializer<?>> getDeserializerClass() {
        return  CustomerDeserializer.class;
    }
}
