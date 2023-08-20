package com.example.userservice.application.service;

import com.example.userservice.adapter.out.persistence.UserEntity;
import com.example.userservice.adapter.out.persistence.UserRepository;
import com.example.userservice.security.CustomUserDetails;
import com.example.userservice.domain.User;
import com.example.userservice.exception.UserNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Override
    public CustomUserDetails loadUserByUsername(String userEmail) {
        try {
            Optional<UserEntity> userEntity = userRepository.findByEmail(userEmail);
            User user = objectMapper.convertValue(userEntity, User.class);

            return new CustomUserDetails(user);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("userNotFound = " + userEmail);
        }
    }
}
