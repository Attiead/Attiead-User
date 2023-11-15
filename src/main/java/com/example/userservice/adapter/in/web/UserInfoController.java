package com.example.userservice.adapter.in.web;

import com.example.userservice.application.port.in.dto.ResponseUserDto;
import com.example.userservice.application.usecase.LoadUserUseCase;
import com.example.userservice.common.response.PageResponseDto;
import com.example.userservice.common.response.ResponseDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserInfoController {

  private final LoadUserUseCase loadUserUseCase;

  @GetMapping
  public PageResponseDto<ResponseUserDto> getAllUsers(
      @RequestParam(value = "page", defaultValue = "0") @Min(0) int page,
      @RequestParam(value = "pageSize", defaultValue = "30") @Min(0) @Max(10000) int pageSize
  ) {
    Pageable pageable = PageRequest.of(page, pageSize);

    return PageResponseDto.success(loadUserUseCase.loadAllUsers(pageable));
  }

  @GetMapping("/{uid}")
  public ResponseDto<ResponseUserDto> getUserInfo(@PathVariable Long uid) {
    return ResponseDto.success(loadUserUseCase.loadUser(uid));
  }
}

