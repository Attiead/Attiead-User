package com.example.userservice.application.usecase;

import com.example.userservice.application.port.in.dto.ResponseUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LoadUserUseCase {

  ResponseUserDTO getUser(String uid);

  Page<ResponseUserDTO> getAllUsers(Pageable pageable);
}
