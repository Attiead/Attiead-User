package com.example.userservice.common.exception;

public class UserNotFoundException extends RuntimeException {

  public UserNotFoundException(String msg) {
    super(msg);
  }
}
