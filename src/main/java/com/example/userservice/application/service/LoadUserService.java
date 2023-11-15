package com.example.userservice.application.service;

import com.example.userservice.adapter.out.persistence.UserPersistenceAdapter;
import com.example.userservice.application.port.in.dto.ResponseUserDto;
import com.example.userservice.application.usecase.LoadUserUseCase;
import com.example.userservice.common.mapper.UserDomainMapper;
import com.example.userservice.domain.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoadUserService implements LoadUserUseCase {

  private UserPersistenceAdapter userPersistenceAdapter;

  @Override
  public Page<ResponseUserDto> loadAllUsers(Pageable pageable) {
    Page<User> savedUsers = userPersistenceAdapter.loadAllUsers(pageable);

    return savedUsers.map(UserDomainMapper.INSTANCE::toResponseUserDto);
  }

  @Override
  public ResponseUserDto loadUser(Long uid) {
    User savedUser = userPersistenceAdapter.loadUser(uid);

    return UserDomainMapper.INSTANCE.toResponseUserDto(savedUser);
  }
}
