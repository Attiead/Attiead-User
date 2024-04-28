package com.example.userservice.infrastructure.kafka.service;

import com.example.userservice.application.service.event.UserLoginEventService;
import com.example.userservice.application.service.event.dto.UserLoginEventDTO;
import com.example.userservice.common.logback.HttpLog;
import com.example.userservice.infrastructure.kafka.dto.KafkaMessageDTO;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLoginKafkaEventService implements UserLoginEventService {

  private final KafkaSendService<UserLoginEventDTO> kafkaSendService;

  @Value("${kafka-topic.user.login}")
  private String topicName;

  private static final String EVENT_HEADER_USER_LOGIN = "USER_LOGIN";
  private static final String EVENT_VERSION = "1";

  private String getRequestId() {
    return Objects.requireNonNull(MDC.get(HttpLog.REQUEST_ID), "Request ID is null");
  }

  @Override
  public void publish(UserLoginEventDTO userLoginEventDTO) {
    KafkaMessageDTO<UserLoginEventDTO> kafkaMessageDTO = new KafkaMessageDTO<>(
        topicName,
        userLoginEventDTO.getUid(),
        userLoginEventDTO,
        EVENT_HEADER_USER_LOGIN,
        getRequestId(),
        EVENT_VERSION
    );

    kafkaSendService.send(kafkaMessageDTO);
  }
}
