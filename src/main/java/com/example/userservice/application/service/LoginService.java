package com.example.userservice.application.service;

import com.example.userservice.adapter.out.persistence.UserEntity;
import com.example.userservice.adapter.out.persistence.UserRepository;
import com.example.userservice.application.port.in.dto.UserAccountDto;
import com.example.userservice.application.usecase.LoginUseCase;
import com.example.userservice.common.exception.ErrorMessages;
import com.example.userservice.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {

  private final UserRepository userRepository;
  private final PasswordEncoder encoder;  // 추가
  private final ObjectMapper objectMapper;

  @Override
  public User login(UserAccountDto userAccountDto) {
    UserEntity userEntity = userRepository.findByEmail(userAccountDto.getEmail())
        .filter(u -> encoder.matches(userAccountDto.getPassword(),
            u.getPassword()))  // 암호화된 비밀번호와 비교하도록 수정
        .orElseThrow(() -> new IllegalArgumentException(ErrorMessages.IDANDPWNOTMATCH.message));

    return objectMapper.convertValue(userEntity, User.class);
  }

}
