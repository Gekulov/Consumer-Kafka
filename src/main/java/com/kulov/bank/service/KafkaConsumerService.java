package com.kulov.bank.service;

import com.kulov.ConsumerConfiguration;
import com.kulov.bank.model.Customer;
import io.dropwizard.lifecycle.Managed;
import org.apache.kafka.clients.consumer.*;


import java.sql.SQLOutput;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class KafkaConsumerService implements Managed {


    private KafkaConsumer<String, Customer> consumer;

    public KafkaConsumerService( ConsumerConfiguration config, List<String> topics) {
        consumerConfig(config);
        this.consumer.subscribe(topics);
    }

    public void consumerConfig(ConsumerConfiguration configuration) {
        Properties configProps = new Properties();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, configuration.getBootstrapServers());
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, configuration.getKeyDeserializer());
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, configuration.getValueDeserializer());
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, configuration.getConsumerGroupId());
        this.consumer = new KafkaConsumer<>(configProps);
    }

    public void start() {

        System.out.println("————————-Consumer———————-");
        while (true) {
            ConsumerRecords<String, Customer> records = this.consumer.poll(Duration.ofMillis(100));

            for (ConsumerRecord<String, Customer> record : records) {
                System.out.printf("Consumer is working : offset = %d, key = %s, topic = %s,  value = %s\n",
                        record.offset(), record.key(), record.topic(), record.value().getFirstName());

            }
        }
    }

    @Override
    public void stop() {
        System.out.println("Shutting down consumer...");
        consumer.wakeup();
    }
}