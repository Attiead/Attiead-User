package com.example.userservice.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @Column(name = "manager_email", nullable = false, length = 100, unique = true)
    private String managerEmail;

    @Column(name = "manager_password", nullable = false, length = 15)
    private String managerPassword;

    @Column(name = "manager_grade", nullable = false, length = 1)
    @ColumnDefault("0")
    private int managerGrade;

    @Column(name = "manager_name", nullable = false, length = 50)
    private String managerName;
}
