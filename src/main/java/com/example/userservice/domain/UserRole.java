package com.example.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
  USER("0"), // 이용자
  ADMIN("1"); // 통합 관리자

  private final String role;
}
