package com.example.userservice.common.exception;

import com.example.userservice.common.response.model.MetaCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InternalServerException extends BaseHttpException {

  public InternalServerException(String message, Object data) {
    super(MetaCode.INTERNAL_SERVER_ERROR, message, data);
  }
}
