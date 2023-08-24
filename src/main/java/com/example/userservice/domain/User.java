package com.example.userservice.domain;

import com.example.userservice.adapter.out.persistence.UserEntity;
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

    public static User userEntityToUser(UserEntity userEntity) {
        return User.builder()
                    .uid(userEntity.getUid())
                    .email(userEntity.getEmail())
                    .password(userEntity.getPassword())
                    .grade(userEntity.getGrade())
                    .status(userEntity.getStatus())
                    .role(userEntity.getRole())
                    .name(userEntity.getName())
                    .nickname(userEntity.getNickname())
                    .comment(userEntity.getComment())
                    .secretStatus(userEntity.getSecretStatus())
                    .build();

    }
}
