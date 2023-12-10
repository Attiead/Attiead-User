package com.example.userservice.application.port.in.dto;

import com.example.userservice.domain.UserGrade;
import com.example.userservice.domain.UserRole;
import com.example.userservice.domain.UserStatus;
import com.example.userservice.domain.Visibilities;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseUserDTO {

  private String uid;
  private String email;
  private String password;
  private UserGrade grade;
  private UserStatus status;
  private UserRole role;
  private String name;
  private String nickname;
  private String biography;
  private Visibilities visibility;
}
