package com.example.userservice.common.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ErrorMessages {

  USER_INFO_NOT_MATCH("로그인에 필요한 정보를 잘못 입력하셨습니다"),
  USER_NOT_FOUND("존재하지 않는 이용자 입니다. 이용자명 : "),
  EXIST_USER("이미 사용 중인 이메일 입니다."),
  ID_AND_PW_NOT_MATCH("아이디 또는 비밀번호가 일치하지 않습니다."),

  JWT_TOKEN_VERIFY_FAILED("토큰 검증에 실패하였습니다.")

  ;

  public final String message;


}
