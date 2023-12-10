package com.example.userservice.application.usecase;

import com.example.userservice.application.port.in.dto.RequestUpdateUserDto;
import com.example.userservice.application.port.in.dto.ResponseUserDto;

public interface UpdateUserUseCase {

  ResponseUserDto update(RequestUpdateUserDto requestUpdateUserDto);

}
