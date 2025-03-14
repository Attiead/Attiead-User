package com.example.userservice.application.port.in.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountDTO {

  private String email;
  private String password;
}
