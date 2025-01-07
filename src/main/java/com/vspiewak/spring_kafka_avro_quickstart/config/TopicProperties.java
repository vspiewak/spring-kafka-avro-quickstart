package com.vspiewak.spring_kafka_avro_quickstart.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka.topics")
public record TopicProperties(
        String customersTopic
) {
}
