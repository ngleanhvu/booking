package com.ngleanhvu.location_service.exception;

public class NotFoundException extends RuntimeException {
    private String resource;
    private String field;
    private String value;
    public NotFoundException(String resource, String field, String value) {
        super(String.format("%s not found with %s: %s", resource, field, value));
    }
}
