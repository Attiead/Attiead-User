package com.example.userservice.application.service;

import com.example.userservice.application.port.in.UserRegistrationUseCase;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService implements UserRegistrationUseCase {
    @Override
    public void registration(String userId) {
        // TODO document why this method is empty
    }
}
