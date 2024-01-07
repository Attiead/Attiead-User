package com.example.userservice.adapter.in.web;

import com.example.userservice.application.port.in.dto.RequestUpdateUserDTO;
import com.example.userservice.application.port.in.dto.ResponseUserDTO;
import com.example.userservice.application.usecase.UpdateUserUseCase;
import com.example.userservice.common.response.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserUpdateController {

  private final UpdateUserUseCase updateUserUseCase;

  @PutMapping
  public ResponseDTO<ResponseUserDTO> updateUserInfo(
      @Valid @RequestBody RequestUpdateUserDTO updateDTO) {
    return ResponseDTO.success(updateUserUseCase.update(updateDTO));
  }
}

