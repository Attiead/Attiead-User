package com.example.userservice.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessages {

  USERINFONOTMATCH("로그인에 필요한 정보를 잘못 입력하셨습니다"),
  USERNOTFOUND("존재하지 않는 이용자 입니다. 이용자명 : "),
  EXISTUSER("이미 사용 중인 이메일 입니다."),
  IDANDPWNOTMATCH("아이디 또는 비밀번호가 일치하지 않습니다."),

  ;

  public final String message;


}
