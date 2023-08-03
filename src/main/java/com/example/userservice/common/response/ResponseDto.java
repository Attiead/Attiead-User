package com.example.userservice.common.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> extends Meta {
    private T data;
}
