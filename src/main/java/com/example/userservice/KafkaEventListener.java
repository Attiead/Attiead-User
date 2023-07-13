package com.example.userservice;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventListener {

    @KafkaListener(topics = {"my-topic"})
    public void handle(KafkaConsumeEvent kafkaConsumeEvent) {
        System.out.println("이벤트 : " + kafkaConsumeEvent.toString());
    }
}
