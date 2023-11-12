package com.example.userservice.application.service;

import com.example.userservice.adapter.out.persistence.UserRepository;
import com.example.userservice.common.exception.ErrorMessages;
import com.example.userservice.common.mapper.UserEntityMapper;
import com.example.userservice.common.security.CustomUserDetails;
import com.example.userservice.common.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public CustomUserDetails loadUserByUsername(String userEmail) {
    return new CustomUserDetails(
        UserEntityMapper.INSTANCE.toUserDomainEntity(userRepository.findByEmail(userEmail)
            .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USERNOTFOUND.message + userEmail))));
  }
}
