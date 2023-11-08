package com.example.userservice.application.service;

import com.example.userservice.adapter.out.persistence.UserRepository;
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
            .orElseThrow(() -> new UserNotFoundException("존재하지 않는 이용자 입니다. 이용자명 : " + userEmail))));
  }
}
