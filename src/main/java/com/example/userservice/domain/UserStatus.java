package com.example.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
  ACTIVE("0"),
  INACTIVE("1");

  private final String status;
}
