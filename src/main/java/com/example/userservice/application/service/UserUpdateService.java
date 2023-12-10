package com.example.userservice.application.service;

import com.example.userservice.adapter.out.persistence.UserPersistenceAdapter;
import com.example.userservice.application.port.in.dto.RequestUpdateUserDTO;
import com.example.userservice.application.port.in.dto.ResponseUserDTO;
import com.example.userservice.application.usecase.UpdateUserUseCase;
import com.example.userservice.common.mapper.UserDomainMapper;
import com.example.userservice.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserUpdateService implements UpdateUserUseCase {

  private UserPersistenceAdapter userPersistenceAdapter;

  @Override
  @Transactional
  public ResponseUserDTO update(RequestUpdateUserDTO requestUpdateUserDTO) {
    User requestUpdateUser = UserDomainMapper.INSTANCE.toUserDomain(requestUpdateUserDTO);
    User savedUser = userPersistenceAdapter.update(requestUpdateUser);

    return UserDomainMapper.INSTANCE.toResponseUserDTO(savedUser);
  }
}
