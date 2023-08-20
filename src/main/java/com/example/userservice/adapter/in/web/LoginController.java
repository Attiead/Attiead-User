package com.example.userservice.adapter.in.web;

import com.example.userservice.adapter.out.persistence.UserEntity;
import com.example.userservice.adapter.out.persistence.UserRepository;
import com.example.userservice.application.port.in.dto.UserAccountDto;
import com.example.userservice.application.usecase.LoginUseCase;
import com.example.userservice.domain.*;
import com.example.userservice.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class LoginController {

    private final LoginUseCase loginUseCase;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    //시큐리티 테스트 api
    @PostMapping("/sign-in")
    public ResponseEntity login(@RequestBody UserAccountDto userAccountDto) {

        User user = loginUseCase.login(userAccountDto);

        return ResponseEntity.ok(tokenProvider.createToken(user.getUid()));
    }

    @PostMapping("/sign-up")
    public String test(String email, String password) {
        UserEntity user = UserEntity.builder()
                .email(email)
                .password(password)
                .grade(UserGrade.GOLD)
                .role(UserRole.STUDENT)
                .status(UserStatus.ACTIVE)
                .name("tj")
                .secretStatus(SecretStatus.OPENED)
                .build();

        UserEntity savedUser = userRepository.save(user);
        return savedUser.getEmail();
    }

    @GetMapping("/test")
    public String test() {

        return "User 서비스의 기본 test ";
    }
}

