package com.example.userservice.application.service.event;

import com.example.userservice.application.service.event.dto.UserLoginEventDTO;

public interface UserLoginEventService {

  void publish(UserLoginEventDTO userLoginEventDTO);
}
