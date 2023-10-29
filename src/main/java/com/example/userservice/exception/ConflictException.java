package com.example.userservice.exception;

import com.example.userservice.common.response.model.MetaCode;

public class ConflictException extends BaseHttpException {

    public ConflictException(String message, Object data) {
        super(MetaCode.INTERNAL_SERVER_ERROR, message, data);
    }
}
