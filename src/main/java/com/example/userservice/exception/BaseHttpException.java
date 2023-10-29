package com.example.userservice.exception;

import com.example.userservice.common.response.model.MetaCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseHttpException extends RuntimeException {

    private final MetaCode metaCode;
    private final String message;
    private final Object data;

    public BaseHttpException() {
        this(MetaCode.INTERNAL_SERVER_ERROR, null, null);
    }

}
