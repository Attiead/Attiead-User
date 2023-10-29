package com.example.userservice.adapter.out.persistence;

import com.example.userservice.application.port.out.CheckExistUserPort;
import com.example.userservice.application.port.out.UserRegistrationPort;
import com.example.userservice.common.mapper.UserEntityMapper;
import com.example.userservice.domain.User;
import com.example.userservice.exception.ExistUserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserPersistenceAdapter implements UserRegistrationPort, CheckExistUserPort {

    private final UserRepository userRepository;

    @Override
    public User register(User user) {
        UserEntity userEntity = UserEntityMapper.INSTANCE.toUserEntity(user);
        UserEntity savedUserEntity = userRepository.save(userEntity);

        return UserEntityMapper.INSTANCE.toUserDomainEntity(savedUserEntity);
    }

    @Override
    public void checkExistUser(String userEmail) {
        if(userRepository.existsByEmail(userEmail)) {
            throw new ExistUserException("이미 사용 중인 이메일 입니다.");
        }
    }
}
