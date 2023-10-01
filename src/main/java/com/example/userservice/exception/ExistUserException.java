package com.example.userservice.exception;

public class ExistUserException extends RuntimeException {

    public ExistUserException(String msg) {
        super(msg);
    }
}
