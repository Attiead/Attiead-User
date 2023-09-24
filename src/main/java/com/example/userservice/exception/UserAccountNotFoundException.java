package com.example.userservice.exception;

public class UserAccountNotFoundException extends RuntimeException {

    public UserAccountNotFoundException(String msg) {
        super(msg);
    }
}
