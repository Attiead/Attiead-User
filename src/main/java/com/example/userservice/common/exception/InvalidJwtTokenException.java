package com.example.userservice.common.exception;

import com.example.userservice.common.response.model.MetaCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidJwtTokenException extends BaseHttpException {

  public InvalidJwtTokenException(String message) {
    super(MetaCode.AUTHENTICATION_FAILED, message, null);
  }
}
