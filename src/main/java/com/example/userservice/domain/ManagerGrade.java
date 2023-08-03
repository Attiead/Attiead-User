package com.example.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ManagerGrade {
    ADMIN("0"), // 통합 관리자
    LEADER("1"); // 권한 별 책임자(예정)

    private final String grade;
}
