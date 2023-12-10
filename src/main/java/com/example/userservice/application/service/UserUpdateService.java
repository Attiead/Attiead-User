package com.example.userservice.application.service;

import com.example.userservice.adapter.out.persistence.UserPersistenceAdapter;
import com.example.userservice.application.port.in.dto.RequestUpdateUserDto;
import com.example.userservice.application.port.in.dto.ResponseUserDto;
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
  public ResponseUserDto update(RequestUpdateUserDto requestUpdateUserDto) {

    User savedUser = userPersistenceAdapter.getUser(requestUpdateUserDto.getUid());
    User requestUpdateUser = UserDomainMapper.INSTANCE.requestUpdateUserDtoToUserDomain(requestUpdateUserDto);

    User returnUser = savedUser.isChangedUser(requestUpdateUser)
        ? userPersistenceAdapter.update(requestUpdateUser)
        : savedUser;

    return UserDomainMapper.INSTANCE.toResponseUserDto(returnUser);
  }
}
