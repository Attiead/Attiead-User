package com.example.userservice;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SampleController {

    private final KafkaProducer kafkaProducer;

    @GetMapping("/kafka")
    public String send() {
        System.out.println("he");
        System.out.println("he");
        kafkaProducer.eventPublish();
        return "Send";
    }
}
