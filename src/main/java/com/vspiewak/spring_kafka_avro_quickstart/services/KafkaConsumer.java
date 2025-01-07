package com.vspiewak.spring_kafka_avro_quickstart.services;

import com.vspiewak.avro.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topics.customersTopic}"
    )
    public void consumeCustomer(
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) int offset,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Payload Customer customer) {

        log.info(
                "Received on partition:{} offset:{} message with key:{} value:{}",
                partition,
                offset,
                key,
                customer
        );

    }
}
