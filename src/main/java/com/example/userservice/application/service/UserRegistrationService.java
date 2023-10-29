package com.example.userservice.application.service;

import com.example.userservice.adapter.out.persistence.UserPersistenceAdapter;
import com.example.userservice.application.port.in.dto.RequestUserDto;
import com.example.userservice.application.port.in.dto.ResponseUserDto;
import com.example.userservice.application.usecase.UserRegistrationUseCase;
import com.example.userservice.common.mapper.UserDomainMapper;
import com.example.userservice.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserRegistrationService implements UserRegistrationUseCase {

    private UserPersistenceAdapter userPersistenceAdapter;

    @Override
    public ResponseUserDto register(RequestUserDto userDto) {
        userPersistenceAdapter.checkExistUser(userDto.getEmail());

        User user = UserDomainMapper.INSTANCE.toUserDomain(userDto);
        User savedUser = userPersistenceAdapter.register(user);

        return UserDomainMapper.INSTANCE.toResponseUserDto(savedUser);
    }
}
