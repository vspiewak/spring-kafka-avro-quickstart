package com.vspiewak.kstream_events_join.services;

import com.vspiewak.avro.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaProducer.class);

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    public void sendCustomer(String topicName, String key, Customer customer) {

        kafkaTemplate
                .send(topicName, key, customer)
                .whenComplete((_, ex) -> {
                    if (ex == null) {
                        log.info("customer sent to topic: {}", customer);
                    } else {
                        log.error("error sending message: {}", ex.getMessage());
                    }
                });

    }

}
