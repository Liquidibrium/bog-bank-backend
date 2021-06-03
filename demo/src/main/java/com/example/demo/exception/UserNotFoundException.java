package com.example.demo.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super("not found username : " + message);
    }

    public UserNotFoundException(Long id) {
        super("not found user by ID : " + id);
    }
}
