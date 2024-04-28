package com.example.userservice.infrastructure.kafka.service;

import com.example.userservice.infrastructure.kafka.dto.KafkaMessageDTO;
import com.example.userservice.infrastructure.kafka.dto.KafkaPayload;
import java.util.Map;
import java.util.concurrent.Executors;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class KafkaSendService<T extends KafkaPayload> {

  private final KafkaTemplate<String, T> kafkaTemplate;

  private static final String EVENT_TYPE_HEADER_NAME = "event_type";
  private static final String EVENT_ID_HEADER_NAME = "event_id";
  private static final String EVENT_VERSION_HEADER_NAME = "event_version";

  public void send(KafkaMessageDTO<T> kafkaMessageDTO) {
    Message<T> message = MessageBuilder
        .withPayload(kafkaMessageDTO.getPayload())
        .setHeader(KafkaHeaders.TOPIC, kafkaMessageDTO.getTopicName())
        .setHeader(KafkaHeaders.KEY, kafkaMessageDTO.getKey())
        .setHeader(EVENT_VERSION_HEADER_NAME, kafkaMessageDTO.getEventVersion())
        .setHeader(EVENT_TYPE_HEADER_NAME, kafkaMessageDTO.getEventName())
        .setHeader(EVENT_ID_HEADER_NAME, kafkaMessageDTO.getEventId())
        .build();

    logPublishedEvent(kafkaMessageDTO);

    // Virtual thread를 사용하여 Kafka 메시지를 비동기적으로 전송
    Executors.newVirtualThreadPerTaskExecutor().submit(() -> kafkaTemplate.send(message));
  }

  private void logPublishedEvent(KafkaMessageDTO<T> kafkaMessageDTO) {
    log.info(Map.of(
        "Kafka Publish Event", kafkaMessageDTO
    ).toString());
  }
}
