package com.example.userservice.adapter.out.persistence;

import com.example.userservice.application.port.out.CheckExistUserPort;
import com.example.userservice.application.port.out.DeleteUserPort;
import com.example.userservice.application.port.out.LoadAllUsersPort;
import com.example.userservice.application.port.out.LoadUserPort;
import com.example.userservice.application.port.out.UpdateUserPort;
import com.example.userservice.application.port.out.UserRegistrationPort;
import com.example.userservice.common.exception.ErrorMessages;
import com.example.userservice.common.exception.UserNotFoundException;
import com.example.userservice.common.mapper.UserEntityMapper;
import com.example.userservice.domain.User;
import com.example.userservice.common.exception.ExistUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter
    implements UserRegistrationPort, CheckExistUserPort, LoadAllUsersPort, LoadUserPort,
    UpdateUserPort, DeleteUserPort {

  private final UserRepository userRepository;

  @Override
  public User register(User user) {
    UserEntity userEntity = UserEntityMapper.INSTANCE.toUserEntity(user);
    UserEntity savedUserEntity = userRepository.save(userEntity);

    return UserEntityMapper.INSTANCE.toUserDomainEntity(savedUserEntity);
  }

  @Override
  public void checkExistUser(String userEmail) {
    if (userRepository.existsByEmail(userEmail)) {
      throw new ExistUserException(ErrorMessages.EXIST_USER.message);
    }
  }

  @Override
  public Page<User> getAllUsers(Pageable pageable) {
    Page<UserEntity> savedUserEntities = userRepository.findAll(pageable);

    return savedUserEntities.map(UserEntityMapper.INSTANCE::toUserDomainEntity);
  }

  @Override
  public User getUser(String uid) {
    UserEntity savedUserEntity = userRepository.findByUid(uid)
        .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND.message));

    return UserEntityMapper.INSTANCE.toUserDomainEntity(savedUserEntity);
  }

  @Override
  public User update(User user) {
    UserEntity savedUserEntity = userRepository.findByUid(user.getUid())
        .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND.message));

    savedUserEntity.update(user);

    return UserEntityMapper.INSTANCE.toUserDomainEntity(savedUserEntity);
  }

  @Override
  public void delete(String uid) {
    UserEntity savedUserEntity = userRepository.findByUid(uid)
        .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USER_NOT_FOUND.message));

    userRepository.delete(savedUserEntity);
  }
}
