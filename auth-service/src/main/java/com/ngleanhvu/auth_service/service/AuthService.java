package com.ngleanhvu.auth_service.service;

import com.ngleanhvu.auth_service.dto.RegisterDto;

import java.io.IOException;

public interface AuthService {
    void register(RegisterDto registerDto) throws IOException;
}
