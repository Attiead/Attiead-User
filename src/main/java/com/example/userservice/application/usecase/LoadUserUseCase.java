package com.example.userservice.application.usecase;

import com.example.userservice.application.port.in.dto.ResponseUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadUserUseCase {

  ResponseUserDto getUser(String uid);

  Page<ResponseUserDto> getAllUsers(Pageable pageable);
}
