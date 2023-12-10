package com.example.userservice.common.config;

import com.example.userservice.common.response.Meta;
import com.example.userservice.common.response.ResponseDTO;
import com.example.userservice.common.response.model.MetaCode;
import com.example.userservice.common.exception.BaseHttpException;
import com.example.userservice.common.exception.ConflictException;
import com.example.userservice.common.exception.InternalServerException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionAdviceHandler {

  @ExceptionHandler(BindException.class)
  public ResponseEntity<ResponseDTO<Object>> bindException(
      BindException e, HttpServletRequest request) {

    return createErrorResponse(HttpStatus.BAD_REQUEST, Objects.requireNonNull(e.getFieldError())
        .getDefaultMessage(), null);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ResponseDTO<Object>> constraintValidationException(
      ConstraintViolationException e, HttpServletRequest request) {

    Stream<Object> errorStream = e.getConstraintViolations().stream().map(m -> {

      StringBuilder errorMessage = new StringBuilder();
      String methodAndFieldName = String.valueOf(m.getPropertyPath());
      int indexOfDot = methodAndFieldName.indexOf(".");
      String fieldName = methodAndFieldName.substring(indexOfDot + 1);

      String validationMessage = m.getMessage();

      return errorMessage.append(fieldName).append(" : ").append(validationMessage);
    });

    String errorMessage = errorStream.findFirst().orElse("").toString();

    return createErrorResponse(HttpStatus.BAD_REQUEST, errorMessage, null);
  }

  @ExceptionHandler({InternalServerException.class})
  public ResponseDTO<Object> handleBaseHttpException(BaseHttpException error) {

    HttpStatus status;

    if (error instanceof ConflictException) {
      status = HttpStatus.CONFLICT;
    } else if (error instanceof InternalServerException) {
      status = HttpStatus.INTERNAL_SERVER_ERROR;
    } else {
      status = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    return createErrorResponse(status, error.getMessage(), error.getData()).getBody();
  }

  private ResponseEntity<ResponseDTO<Object>> createErrorResponse(
      HttpStatus statusCode,
      String message,
      Object data
  ) {
    MetaCode dtoMetaCode = MetaCode.valueFrom(statusCode);

    ResponseDTO<Object> response = new ResponseDTO<>(
        new Meta(
            dtoMetaCode,
            dtoMetaCode.code,
            message
        ),
        data
    );
    return new ResponseEntity<>(response, statusCode);
  }

}
