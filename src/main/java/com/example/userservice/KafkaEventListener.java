package com.example.userservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventListener {

    @KafkaListener(topics = {"my-topic"})
    public void handle(KafkaProduceEvent kafkaProduceEvent) {
        System.out.println("이벤트 : " + kafkaProduceEvent.toString());
    }
}
