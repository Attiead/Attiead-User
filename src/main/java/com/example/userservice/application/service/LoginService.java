package com.example.userservice.application.service;

import com.example.userservice.adapter.out.persistence.UserEntity;
import com.example.userservice.adapter.out.persistence.UserRepository;
import com.example.userservice.application.usecase.LoginUseCase;
import com.example.userservice.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements LoginUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;	// 추가
    private final TokenProvider tokenProvider;	// 추가

    @Override
    public String login(String email, String password) {
        UserEntity user = userRepository.findByEmail(email)
                .filter(u -> encoder.matches(password, u.getPassword()))	// 암호화된 비밀번호와 비교하도록 수정
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다."));

        //create token using Domain Entity
        return tokenProvider.createToken(user.getUid());
    }

}
