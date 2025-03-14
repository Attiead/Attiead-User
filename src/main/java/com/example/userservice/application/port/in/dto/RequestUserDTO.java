package com.example.userservice.application.port.in.dto;

import com.example.userservice.domain.UserGrade;
import com.example.userservice.domain.UserRole;
import com.example.userservice.domain.UserStatus;
import com.example.userservice.domain.Visibilities;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserDTO {

  @NotBlank(message = "이메일은 필수 입력 값 입니다.")
  @Email(message = "이메일 형식이 맞지 않습니다.")
  private String email;
  @NotBlank(message = "비밀번호는 필수 입력 값 입니다.")
  private String password;
  private UserGrade grade;
  private UserStatus status;
  private UserRole role;
  @NotBlank(message = "이용자 이름은 필수 입력 값 입니다.")
  private String name;
  private String nickname;
  private String biography;
  private Visibilities visibility;
}
