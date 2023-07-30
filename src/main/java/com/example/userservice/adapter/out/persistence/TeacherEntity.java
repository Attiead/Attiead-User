package com.example.userservice.adapter.out.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Entity
@Table(name = "teacher")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false, length = 100)
    private Long sid;

    @Column(name = "teacher_email", nullable = false, length = 100, unique = true)
    private String teacherEmail;

    @Column(name = "teacher_password", nullable = false, length = 15)
    private String teacherPassword;

    @Column(name = "teacher_status", nullable = false, length = 1)
    @ColumnDefault("true")
    private boolean teacherStatus;

    @Column(name = "teacher_name", nullable = false, length = 50)
    private String teacherName;

    @Column(name = "comment", nullable = true, length = 100)
    private String comment;

    @Column(name = "img_path", nullable = true, length = 20)
    private String imgPath;

    @Column(name = "create_dttm", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createDttm;

    @Column(name = "subject", nullable = false, length = 20)
    private String subject;


}

