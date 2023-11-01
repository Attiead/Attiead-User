package com.example.userservice.application.port.in.dto;

import com.example.userservice.domain.UserGrade;
import com.example.userservice.domain.UserStatus;
import com.example.userservice.domain.Visibilities;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseUserDto {

    private UUID uid;
    private String email;
    private String password;
    private UserGrade grade;
    private UserStatus status;
    private String name;
    private String nickname;
    private String biography;
    private Visibilities visibility;
}
