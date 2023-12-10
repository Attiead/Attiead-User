package com.example.userservice.application.usecase;

import com.example.userservice.application.port.in.dto.RequestUserDTO;
import com.example.userservice.application.port.in.dto.ResponseUserDTO;

public interface UserRegistrationUseCase {

  ResponseUserDTO register(RequestUserDTO userDTO);
}
