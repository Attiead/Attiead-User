package com.example.userservice.adapter.out.persistence;

import com.example.userservice.common.response.model.BaseEntity;
import com.example.userservice.domain.Visibilities;
import com.example.userservice.domain.UserGrade;
import com.example.userservice.domain.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @Column(name="uid", nullable = false)
    @Builder.Default private UUID uid = UUID.randomUUID();

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 15)
    private String password;

    @Column(name = "grade", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default private UserGrade grade = UserGrade.BRONZE;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default private UserStatus status = UserStatus.ACTIVE;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "nickname", nullable = true, length = 50)
    private String nickname;

    @Column(name = "biography", nullable = true, length = 100)
    private String biography;

    @Column(name = "visibilities", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default private Visibilities visibility = Visibilities.OPENED;
}
