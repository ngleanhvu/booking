package com.ngleanhvu.common.response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    private String message;
    private String code;
    private T metadata;

    public static <T> ResponseEntity<ApiResponse<T>> success(String message, T metadata) {
        ApiResponse<T> response = new ApiResponse<>(message, HttpStatus.OK.name(), metadata);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(String message, T metadata, HttpStatus status) {
        ApiResponse<T> response = new ApiResponse<>(message, status.name(), metadata);
        return new ResponseEntity<>(response, status);
    }
}
