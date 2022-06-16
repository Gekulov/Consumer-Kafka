package com.kulov.bank;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kulov.bank.model.Customer;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class CustomerDeserializer implements Deserializer<Customer>{

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public Customer deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        Customer customer = null;
        try {
            customer = mapper.readValue(bytes, Customer.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }


    @Override
    public Customer deserialize(String topic, Headers headers, byte[] data) {
        return Deserializer.super.deserialize(topic, headers, data);
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}
