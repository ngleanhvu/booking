package com.ngleanhvu.auth_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegisterDto {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank
    private String fullName;

    @NotBlank
    @Pattern(regexp = "^\\d{10,15}$")
    private String phone;

    @NotNull
    private MultipartFile avatar;
}