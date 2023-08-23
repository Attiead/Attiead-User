package com.example.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;


@Getter
@Builder
@AllArgsConstructor
public class User {

    private UUID uid;
    private String email;
    private String password;
    private UserGrade grade;
    private UserStatus status;
    private UserRole role;
    private String name;
    private String nickname;
    private String comment;
    private SecretStatus secretStatus;

}
