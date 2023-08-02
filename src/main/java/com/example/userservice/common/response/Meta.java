package com.example.userservice.common.response;

import com.example.userservice.common.response.model.MetaCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Meta {
    private MetaCode code;
    private String type;
    private String message;
}
