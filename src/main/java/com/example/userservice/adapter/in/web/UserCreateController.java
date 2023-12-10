package com.example.userservice.adapter.in.web;

import com.example.userservice.application.port.in.dto.RequestUserDTO;
import com.example.userservice.application.port.in.dto.ResponseUserDTO;
import com.example.userservice.application.usecase.UserRegistrationUseCase;
import com.example.userservice.common.response.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserCreateController {

  private final UserRegistrationUseCase userRegistrationUseCase;

  @PostMapping("/sign-up")
  public ResponseDTO<ResponseUserDTO> signUp(@Valid @RequestBody RequestUserDTO userDTO) {
    return ResponseDTO.success(userRegistrationUseCase.register(userDTO));
  }

  @GetMapping("/test")
  public ResponseDTO test() {
    return ResponseDTO.success("User 서비스의 기본 test ");
  }
}

