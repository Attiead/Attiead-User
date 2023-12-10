package com.example.userservice.adapter.out.persistence;

import com.example.userservice.common.response.model.BaseEntity;
import com.example.userservice.domain.User;
import com.example.userservice.domain.UserRole;
import com.example.userservice.domain.Visibilities;
import com.example.userservice.domain.UserGrade;
import com.example.userservice.domain.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity extends BaseEntity {

  @Column(name = "uid", nullable = false, length = 36)
  @Builder.Default
  private String uid = UUID.randomUUID().toString();

  @Column(name = "email", nullable = false, length = 100, unique = true)
  private String email;

  @Column(name = "password", nullable = false, length = 15)
  private String password;

  @Column(name = "grade", nullable = false)
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private UserGrade grade = UserGrade.BRONZE;

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private UserStatus status = UserStatus.ACTIVE;

  @Column(name = "role", nullable = false)
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private UserRole role = UserRole.USER;

  @Column(name = "name", nullable = false, length = 50)
  private String name;

  @Column(name = "nickname", nullable = true, length = 50)
  private String nickname;

  @Column(name = "biography", nullable = true, length = 100)
  private String biography;

  @Column(name = "visibilities", nullable = false)
  @Enumerated(EnumType.STRING)
  @Builder.Default
  private Visibilities visibility = Visibilities.OPENED;

  public void update(User updatedUser) {
    this.password = updatedUser.getPassword();
    this.grade = updatedUser.getGrade();
    this.role = updatedUser.getRole();
    this.name = updatedUser.getName();
    this.nickname = updatedUser.getNickname();
    this.status = updatedUser.getStatus();
    this.biography = updatedUser.getBiography();
    this.visibility = updatedUser.getVisibility();
  }
}
