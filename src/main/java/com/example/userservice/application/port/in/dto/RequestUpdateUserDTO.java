package com.example.userservice.application.port.in.dto;

import com.example.userservice.domain.UserGrade;
import com.example.userservice.domain.UserRole;
import com.example.userservice.domain.UserStatus;
import com.example.userservice.domain.Visibilities;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUpdateUserDTO {

  @NotBlank(message = "uid는 필수 값입니다.")
  private String uid;
  private String password;
  private UserGrade grade;
  private UserStatus status;
  private UserRole role;
  private String name;
  private String nickname;
  private String biography;
  private Visibilities visibility;
}
