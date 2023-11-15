package com.example.userservice.application.usecase;

import com.example.userservice.application.port.in.dto.ResponseUserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadUserUseCase {

  ResponseUserDto loadUser(Long uid);

  Page<ResponseUserDto> loadAllUsers(Pageable pageable);
}
