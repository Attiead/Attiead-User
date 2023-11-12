package com.example.userservice.common.exception;

public class UserAccountNotFoundException extends RuntimeException {

  public UserAccountNotFoundException(String msg) {
    super(msg);
  }
}
