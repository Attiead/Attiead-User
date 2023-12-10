package com.example.userservice.application.usecase;

import com.example.userservice.application.port.in.dto.RequestUpdateUserDTO;
import com.example.userservice.application.port.in.dto.ResponseUserDTO;

public interface UpdateUserUseCase {

  ResponseUserDTO update(RequestUpdateUserDTO requestUpdateUserDTO);

}
