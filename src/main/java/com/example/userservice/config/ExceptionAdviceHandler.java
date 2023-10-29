package com.example.userservice.config;

import com.example.userservice.common.response.Meta;
import com.example.userservice.common.response.ResponseDto;
import com.example.userservice.common.response.model.MetaCode;
import com.example.userservice.exception.BaseHttpException;
import com.example.userservice.exception.ConflictException;
import com.example.userservice.exception.InternalServerException;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<ResponseDto<Object>> bindException(BindException e, HttpServletRequest request) {
        log.error("BindException 발생!! trace:{}", e.getStackTrace());
        return createErrorResponse(HttpStatus.BAD_REQUEST, e.getFieldError().getDefaultMessage(), null);
    }

    @ExceptionHandler(
        {
            InternalServerException.class
        }
    )
    public ResponseDto<Object> handleBaseHttpException(BaseHttpException error) {

        HttpStatus status;

//        if (error instanceof InvalidException) {
//            status = HttpStatus.BAD_REQUEST;
        if (error instanceof ConflictException) {
            status = HttpStatus.CONFLICT;
//        } else if (error instanceof NotFoundException) {
//            status = HttpStatus.NOT_FOUND;
        } else if (error instanceof InternalServerException) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return createErrorResponse(status, error.getMessage(), error.getData()).getBody();
    }

    private ResponseEntity<ResponseDto<Object>> createErrorResponse(
        HttpStatus statusCode,
        String message,
        Object data
    ) {
        MetaCode dtoMetaCode = MetaCode.valueFrom(statusCode);

        ResponseDto<Object> response = new ResponseDto<>(
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
