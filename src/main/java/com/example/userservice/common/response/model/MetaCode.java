package com.example.userservice.common.response.model;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum MetaCode {

    SUCCESS("20000000"),
    CREATED("20100000"),
    ACCEPTED("20200000"),
    NO_CONTENT("20400000"),
    BAD_REQUEST("40000000"),
    AUTHENTICATION_FAILED("40100000"),
    FORBIDDEN("40300000"),
    NOT_FOUND("40400000"),
    METHOD_NOW_ALLOWED("40500000"),
    NOT_ACCEPTABLE("40600000"),
    CONFLICT("40900000"),
    UNSUPPORTED_MEDIA_TYPE("41500000"),
    UNPROCESSABLE_ENTITY("42200000"),
    THROTTLED("42900000"),
    INTERNAL_SERVER_ERROR("50000000"),
    NOT_IMPLEMENTED("50100000"),
    SERVICE_UNAVAILABLE("50300000");

    public static final String DEFAULT_META_CODE = "00000";
    private final String code;

    public static MetaCode valueFrom(HttpStatus status) {
        return switch (status) {
            case OK -> SUCCESS;
            case NO_CONTENT -> NO_CONTENT;
            case CREATED -> CREATED;
            case BAD_REQUEST -> BAD_REQUEST;
            case NOT_FOUND -> NOT_FOUND;
            case CONFLICT -> CONFLICT;
            case NOT_IMPLEMENTED -> NOT_IMPLEMENTED;
            case INTERNAL_SERVER_ERROR -> INTERNAL_SERVER_ERROR;
            case SERVICE_UNAVAILABLE -> SERVICE_UNAVAILABLE;
            case UNPROCESSABLE_ENTITY -> UNPROCESSABLE_ENTITY;
            default -> throw new IllegalArgumentException("Please provide correct status.");
        };
    }
}
