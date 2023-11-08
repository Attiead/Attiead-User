package com.example.userservice.application.usecase;

import com.example.userservice.application.port.in.dto.RequestUserDto;
import com.example.userservice.application.port.in.dto.ResponseUserDto;

public interface UserRegistrationUseCase {

  ResponseUserDto register(RequestUserDto userDto);
}
