package com.example.userservice.application.service;

import com.example.userservice.application.usecase.AuthorizeUserUseCase;
import com.example.userservice.common.security.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VerifyUserService implements AuthorizeUserUseCase {

  @Override
  public String verifyUser(String token) {
    String uid = TokenProvider.validToken(token);

    return uid;
  }
}
