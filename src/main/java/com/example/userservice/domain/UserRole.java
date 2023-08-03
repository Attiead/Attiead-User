package com.example.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    STUDENT("0"),
    TEACHER("1");

    private final String role;
}
