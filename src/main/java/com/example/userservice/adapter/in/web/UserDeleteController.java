package com.example.userservice.adapter.in.web;

import com.example.userservice.application.usecase.DeleteUserUseCase;
import com.example.userservice.common.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserDeleteController {

  private final DeleteUserUseCase deleteUserUseCase;

  @DeleteMapping("/{uid}")
  public ResponseDTO<String> deleteUser(@PathVariable String uid) {
    deleteUserUseCase.delete(uid);
    return ResponseDTO.success(uid);
  }
}

