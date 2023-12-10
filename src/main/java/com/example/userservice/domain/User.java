package com.example.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class User {

  private String uid;
  private String email;
  private String password;
  private UserGrade grade;
  private UserStatus status;
  private UserRole role;
  private String name;
  private String nickname;
  private String biography;
  private Visibilities visibility;

  public boolean isChangedUser(User user) {
    boolean isEquals = true;
    if (this.uid.equals(user.uid)
        && (this.password.equals(user.password) && this.grade.equals(user.grade)
            && this.status.equals(user.status) && this.role.equals(user.role)
            && this.name.equals(user.name) && this.nickname.equals(user.nickname)
            && this.biography.equals(user.biography) && this.visibility.equals(user.visibility))
    ) {
      isEquals = false;
    }
    return isEquals;
  }
}
