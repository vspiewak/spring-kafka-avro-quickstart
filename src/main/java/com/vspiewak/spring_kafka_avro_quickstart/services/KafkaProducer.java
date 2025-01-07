package com.vspiewak.spring_kafka_avro_quickstart.services;

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

    public void sendCustomer(String topicName, Customer customer) {

        kafkaTemplate
                .send(topicName, customer.getCustomerId(), customer)
                .whenComplete((_, ex) -> {
                    if (ex == null) {
                        log.info("customer sent to topic: {}", customer);
                    } else {
                        log.error("error sending message: {}", ex.getMessage());
                    }
                });

    }

}
