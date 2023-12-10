package com.example.userservice.adapter.in.web;

import com.example.userservice.application.port.in.dto.RequestUpdateUserDto;
import com.example.userservice.application.port.in.dto.ResponseUserDto;
import com.example.userservice.application.usecase.UpdateUserUseCase;
import com.example.userservice.common.response.ResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserUpdateController {

  private final UpdateUserUseCase updateUserUseCase;

  @PutMapping
  public ResponseDto<ResponseUserDto> updateUserInfo(
      @Valid @RequestBody RequestUpdateUserDto updateDto) {
    return ResponseDto.success(updateUserUseCase.update(updateDto));
  }
}

