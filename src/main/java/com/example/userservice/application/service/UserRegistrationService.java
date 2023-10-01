package com.example.userservice.application.service;

import com.example.userservice.adapter.out.persistence.UserRepository;
import com.example.userservice.application.usecase.UserRegistrationUseCase;
import com.example.userservice.domain.User;
import com.example.userservice.exception.ExistUserException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegistrationService implements UserRegistrationUseCase {

    private UserRepository userRepository;

    @Override
    public User register(User user) {
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new ExistUserException("이미 사용 중인 이메일 입니다.");
        }

        return User.userEntityToUser(userRepository.save(User.userToUserEntity(user)));
    }
}
