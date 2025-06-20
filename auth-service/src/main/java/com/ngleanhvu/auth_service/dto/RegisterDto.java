package com.ngleanhvu.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    private MultipartFile avatar;
}