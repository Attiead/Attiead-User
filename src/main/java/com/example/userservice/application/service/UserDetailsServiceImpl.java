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

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String userEmail) {
        try {
            return new CustomUserDetails(
                    User.userEntityToUser(userRepository.findByEmail(userEmail)
                            .orElseThrow(() -> new NoSuchElementException("UserEntity not found"))));
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException("userNotFound = " + userEmail);
        }
    }
}
