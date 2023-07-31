package com.example.userservice.adapter.out.persistence;

import com.example.userservice.domain.SecretStatus;
import com.example.userservice.domain.UserGrade;
import com.example.userservice.domain.UserRole;
import com.example.userservice.domain.UserStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false, length = 100)
    private Long sid;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false, length = 100, unique = true)
    private Long uid;

    @Column(name = "user_email", nullable = false, length = 100, unique = true)
    private String userEmail;

    @Column(name = "user_password", nullable = false, length = 15)
    private String userPassword;

    @Column(name = "user_grade", nullable = false, length = 1)
    @ColumnDefault("0")
    @Enumerated(EnumType.STRING)
    private UserGrade userGrade;

    @Column(name = "user_status", nullable = false, length = 1)
    @ColumnDefault("0")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(name = "user_status", nullable = false, length = 1)
    @ColumnDefault("0")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "nick_name", nullable = true, length = 50)
    private String nickName;

    @Column(name = "comment", nullable = true, length = 100)
    private String comment;

    @Column(name = "create_dttm", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createDttm;

    @Column(name = "secret_status", nullable = true, length = 1)
    @ColumnDefault("0")
    private SecretStatus secretStatus;

}
