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
    private String biography;
    private Visibilities visibilities;

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
                    .biography(userEntity.getBiography())
                    .visibilities(userEntity.getVisibilities())
                    .build();

    }

    public static UserEntity userToUserEntity(User user) {
        return UserEntity.builder()
            .email(user.getEmail())
            .password(user.getPassword())
            .grade(user.getGrade() == null ? UserGrade.BRONZE : user.getGrade())
            .status(user.getStatus() == null ? UserStatus.ACTIVE : user.getStatus())
            .role(user.getRole() == null ? UserRole.STUDENT : user.getRole())
            .name(user.getName())
            .nickname(user.getNickname())
            .biography(user.getBiography())
            .visibilities(user.getVisibilities() == null ? Visibilities.OPENED : user.getVisibilities())
            .build();
    }
}
