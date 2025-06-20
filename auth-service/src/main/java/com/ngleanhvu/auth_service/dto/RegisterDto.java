package com.ngleanhvu.auth_service.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class AuthRegisterDto {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;
    private String phone;
    private MultipartFile avatar;
}
