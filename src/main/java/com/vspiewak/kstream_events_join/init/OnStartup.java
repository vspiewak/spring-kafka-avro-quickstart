package com.vspiewak.kstream_events_join.init;

import com.vspiewak.avro.Customer;
import com.vspiewak.kstream_events_join.services.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Component
public class OnStartup {

    @Value("${spring.kafka.topics.customersTopic}")
    String customersTopic;

    @Autowired
    private KafkaProducer kafkaProducer;

    @EventListener(ApplicationReadyEvent.class)
    public void onStartup() {

        kafkaProducer.sendCustomer(
                customersTopic,
                "customer-id-1",
                Customer
                        .newBuilder()
                        .setFirstname("Vincent")
                        .setLastname("Spiewak")
                        .build()
        );

    }

}
