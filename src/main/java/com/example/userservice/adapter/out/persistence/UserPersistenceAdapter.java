package com.example.userservice.adapter.out.persistence;

import com.example.userservice.application.port.out.CheckExistUserPort;
import com.example.userservice.application.port.out.LoadAllUsersPort;
import com.example.userservice.application.port.out.LoadUserPort;
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
    implements UserRegistrationPort, CheckExistUserPort, LoadAllUsersPort, LoadUserPort {

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
      throw new ExistUserException(ErrorMessages.EXISTUSER.message);
    }
  }

  @Override
  public Page<User> loadAllUsers(Pageable pageable) {
    Page<UserEntity> savedUserEntities = userRepository.findAll(pageable);

    return savedUserEntities.map(UserEntityMapper.INSTANCE::toUserDomainEntity);
  }

  @Override
  public User loadUser(Long uid) {
    UserEntity savedUserEntity = userRepository.findById(uid)
        .orElseThrow(() -> new UserNotFoundException(ErrorMessages.USERNOTFOUND.message));

    return UserEntityMapper.INSTANCE.toUserDomainEntity(savedUserEntity);
  }
}
