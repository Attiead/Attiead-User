package com.example.userservice.infrastructure.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class KafkaProduceEvent {

  private String title;
  private String content;
}
