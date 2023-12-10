package com.example.userservice.application.usecase;

import com.example.userservice.application.port.in.dto.UserAccountDTO;
import com.example.userservice.domain.User;


public interface LoginUseCase {

  User login(UserAccountDTO userAccountDTO);

}
