package com.example.userservice.application.usecase;

import com.example.userservice.application.port.in.dto.UserAccountDto;
import com.example.userservice.domain.User;


public interface LoginUseCase {
    User login(UserAccountDto userAccountDto);

}
