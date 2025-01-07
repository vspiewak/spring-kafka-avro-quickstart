package com.vspiewak.kstream_events_join.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.kafka.topics")
public record TopicProperties(
        String customersTopic
) {
}
