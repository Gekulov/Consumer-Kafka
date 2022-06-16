package com.kulov.bank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kulov.ConsumerConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public class BankConfiguration extends Configuration {

    private static final String DATABASE = "database";

    @Valid
    @NotNull
    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

    @Valid
    @NotNull
    @JsonProperty("consumer")
    private ConsumerConfiguration kafkaConsumerConfiguration;

    @Valid
    @NotNull
    @JsonProperty("topics")
    private List<String> topics;

    @JsonProperty(DATABASE)
    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    @JsonProperty(DATABASE)
    public void setDataSourceFactory(final DataSourceFactory dataSourceFactory) {
        this.dataSourceFactory = dataSourceFactory;
    }

    public ConsumerConfiguration getKafkaConsumerConfiguration() {
        return kafkaConsumerConfiguration;
    }

    public void setKafkaConsumerConfiguration(ConsumerConfiguration kafkaConsumerConfiguration) {
        this.kafkaConsumerConfiguration = kafkaConsumerConfiguration;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }
}
