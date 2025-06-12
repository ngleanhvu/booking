package com.ngleanhvu.user_service.exception;

public class UserNotFoundException extends RuntimeException {
    private String resource;
    private String field;
    private String value;

    public UserNotFoundException(String resource, String field, String value) {
        super(String.format("%s not found with %s: %s", resource, field, value));
    }
}
