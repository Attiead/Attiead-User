package com.example.userservice.exception;

import com.example.userservice.common.response.model.MetaCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends BaseHttpException {

    public InternalServerException(String message, Object data) {
        super(MetaCode.INTERNAL_SERVER_ERROR, message, data);
    }
}
