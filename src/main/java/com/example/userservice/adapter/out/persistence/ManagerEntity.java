package com.example.userservice.adapter.out.persistence;

import com.example.userservice.domain.ManagerGrade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "manager")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ManagerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false, length = 100)
    private Long sid;

    @GeneratedValue
    @Column(name="mid", nullable = false, length = 36, columnDefinition = "BINARY(16)")
    private UUID mid;

    @Column(name = "manager_email", nullable = false, length = 100, unique = true)
    private String managerEmail;

    @Column(name = "manager_password", nullable = false, length = 15)
    private String managerPassword;

    @Column(name = "manager_grade", nullable = false)
    @Enumerated(EnumType.STRING)
    private ManagerGrade managerGrade;

    @Column(name = "manager_name", nullable = false, length = 50)
    private String managerName;
}
