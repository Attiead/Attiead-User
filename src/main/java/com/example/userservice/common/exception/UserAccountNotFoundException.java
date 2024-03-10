package com.example.userservice.common.exception;

import com.example.userservice.common.response.model.MetaCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserAccountNotFoundException extends BaseHttpException {

  public UserAccountNotFoundException(String message) {
    super(MetaCode.NOT_FOUND, message, null);
  }
}
