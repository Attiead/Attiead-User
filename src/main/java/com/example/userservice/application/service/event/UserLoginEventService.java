package com.example.userservice.application.service.event;

import com.example.userservice.application.service.event.dto.UserLoginEventDTO;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserLoginEventService {

  void publish(UserLoginEventDTO userLoginEventDTO) throws JsonProcessingException;
}
