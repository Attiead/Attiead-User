package com.example.userservice.adapter.in.web;

import com.example.userservice.application.usecase.AuthorizeUserUseCase;
import com.example.userservice.common.response.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserAuthorizeController {

  private final AuthorizeUserUseCase authorizeUserUseCase;

  @GetMapping("/authorize")
  public ResponseDTO<String> getUserAuthorize(
      @RequestHeader(name = HttpHeaders.AUTHORIZATION) String token) {

    return ResponseDTO.success(authorizeUserUseCase.verifyUser(token));
  }
}

