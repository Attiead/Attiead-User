package com.example.userservice.application.service.event.dto;

import com.example.userservice.infrastructure.kafka.dto.KafkaPayload;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserLoginEventDTO implements KafkaPayload {

  private String uid;

  @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssz", timezone = "UTC")
  private String loginTime;
}
