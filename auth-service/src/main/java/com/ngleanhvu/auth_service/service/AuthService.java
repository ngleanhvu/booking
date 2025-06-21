package com.ngleanhvu.auth_service.service;

import com.ngleanhvu.auth_service.dto.LoginRequest;
import com.ngleanhvu.auth_service.dto.LoginResponse;
import com.ngleanhvu.auth_service.dto.RegisterDto;

import java.io.IOException;

public interface AuthService {
    void register(RegisterDto registerDto) throws IOException;
    LoginResponse login(LoginRequest loginRequest) throws Exception;
    void logout(String refreshToken) throws IOException;
    String refresh(String refreshToken) throws Exception;
}
