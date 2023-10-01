package com.example.userservice.adapter.in.web;

import com.example.userservice.application.usecase.UserRegistrationUseCase;
import com.example.userservice.common.response.ResponseDto;
import com.example.userservice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRegistrationUseCase userRegistrationUseCase;

    @PostMapping("/sign-up")
    public ResponseDto<User> singUp(@RequestBody User user) {

        return ResponseDto.success(userRegistrationUseCase.register(user));
    }

    @GetMapping("/test")
    public String test() {

        return "User 서비스의 기본 test ";
    }
}

