package com.ngleanhvu.auth_service.service;

import com.ngleanhvu.auth_service.dto.AuthRegisterDto;
import com.ngleanhvu.auth_service.entity.Auth;

public interface AuthService {
    void createAuth(AuthRegisterDto authRegisterDto);
}
