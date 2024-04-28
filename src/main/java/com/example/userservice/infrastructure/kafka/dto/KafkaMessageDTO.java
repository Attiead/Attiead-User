package com.example.userservice.infrastructure.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Builder
@Getter
public class KafkaMessageDTO<T extends KafkaPayload> {

  private String topicName;
  private String key;
  private T payload;
  private String eventName;
  private String eventId;
  private String eventVersion;
}
