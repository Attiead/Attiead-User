package com.example.userservice.common.exception;

public class ExistUserException extends RuntimeException {

  public ExistUserException(String msg) {
    super(msg);
  }
}
