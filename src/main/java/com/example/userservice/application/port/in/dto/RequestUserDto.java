package com.example.userservice.application.port.in.dto;

import com.example.userservice.domain.UserGrade;
import com.example.userservice.domain.UserRole;
import com.example.userservice.domain.UserStatus;
import com.example.userservice.domain.Visibilities;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.wildfly.common.annotation.NotNull;

@Getter
@AllArgsConstructor
public class RequestUserDto {

    @Email
    private String email;
    @NotNull
    private String password;
    private UserGrade grade;
    private UserStatus status;
    private UserRole role;
    @NotNull
    private String name;
    private String nickname;
    private String biography;
    private Visibilities visibility;
}
