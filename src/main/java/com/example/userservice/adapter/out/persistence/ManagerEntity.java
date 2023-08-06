package com.example.userservice.adapter.out.persistence;

import com.example.userservice.common.response.model.BaseEntity;
import com.example.userservice.domain.ManagerGrade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "managers")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerEntity extends BaseEntity {

    @Column(name="mid", nullable = false, columnDefinition = "BINARY(16)")
    @Builder.Default private UUID mid = UUID.randomUUID();

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 15)
    private String password;

    @Column(name = "grade", nullable = false)
    @Enumerated(EnumType.STRING)
    private ManagerGrade grade;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}
