package com.example.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class User {

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
