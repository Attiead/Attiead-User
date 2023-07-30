package com.example.userservice.adapter.out.persistence;

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

    @Column(name = "user_email", nullable = false, length = 100, unique = true)
    private String userEmail;

    @Column(name = "user_password", nullable = false, length = 15)
    private String userPassword;

    @Column(name = "user_grade", nullable = false, length = 1)
    @ColumnDefault("0")
    private int userGrade;

    @Column(name = "user_status", nullable = false, length = 1)
    @ColumnDefault("true")
    private boolean userStatus;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "nick_name", nullable = true, length = 50)
    private String nickName;

    @Column(name = "comment", nullable = true, length = 100)
    private String comment;

    @Column(name = "img_path", nullable = true, length = 20)
    private String imgPath;

    @Column(name = "create_dttm", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createDttm;

    @Column(name = "secret_status", nullable = true, length = 20)
    @ColumnDefault("true")
    private boolean secretStatus;

}
