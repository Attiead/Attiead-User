package com.example.userservice.application.service;

import com.example.userservice.adapter.out.persistence.UserRepository;
import com.example.userservice.security.CustomUserDetails;
import com.example.userservice.domain.User;
import com.example.userservice.exception.UserNotFoundException;
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
                User.userEntityToUser(userRepository.findByEmail(userEmail)
                        .orElseThrow(() -> new UserNotFoundException("userNotFound = " + userEmail))));
    }
}
