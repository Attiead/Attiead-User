package com.example.userservice.adapter.in.web;

import com.example.userservice.application.usecase.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class LoginController {

    private final LoginUseCase loginUseCase;

    //시큐리티 테스트 api
    @PostMapping("/sign-in")
    public ResponseEntity login(String email, String password) {

        String token = loginUseCase.login(email, password);

        return ResponseEntity.ok(token);
    }
}

