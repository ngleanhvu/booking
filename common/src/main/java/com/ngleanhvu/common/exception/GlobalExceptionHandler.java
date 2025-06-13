package com.ngleanhvu.common.exception;

import com.ngleanhvu.common.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ApiResponse.error(ex.getMessage(), null, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return ApiResponse.error(ex.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
