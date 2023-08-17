package com.example.userservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {

    private UserAccount userAccount;
    private UserGrade grade;
    private UserStatus status;
    private UserRole role;
    private String name;
    private String nickname;
    private String comment;
    private SecretStatus secretStatus;

}
