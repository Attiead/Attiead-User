package com.example.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserGrade {
  GOLD("2"),
  SILVER("1"),
  BRONZE("0");

  private final String grade;
}
