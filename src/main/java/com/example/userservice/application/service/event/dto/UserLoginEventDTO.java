package com.example.userservice.application.service.event.dto;

import com.example.userservice.infrastructure.kafka.dto.KafkaPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserLoginEventDTO implements KafkaPayload {

  private String uid;
  private String loginTime;
}
