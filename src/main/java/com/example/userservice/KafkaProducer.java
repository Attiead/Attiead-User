package com.example.userservice;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, KafkaProduceEvent> kafkaTemplate;

    private String topicName = "my-topic";

    public void eventPublish() {
        kafkaTemplate.send(topicName, new KafkaProduceEvent("title", "content"));
    }

}
