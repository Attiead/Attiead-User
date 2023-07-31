package com.example.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SecretStatus {
    OPENED("0"),
    CLOSED("1");

    private final String status;
}
