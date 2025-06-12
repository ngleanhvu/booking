package com.ngleanhvu.location_service.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter @AllArgsConstructor
public class ErrorResponseDto {
    private HttpStatus errorCode;
    private String message;
}
