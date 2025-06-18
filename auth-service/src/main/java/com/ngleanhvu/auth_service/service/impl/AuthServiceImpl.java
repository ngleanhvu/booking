package com.ngleanhvu.auth_service.service.impl;

import com.ngleanhvu.auth_service.dto.AuthRegisterDto;
import com.ngleanhvu.auth_service.repository.AuthRepository;
import com.ngleanhvu.auth_service.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;

    @Override
    public void createAuth(AuthRegisterDto authRegisterDto) {

    }
}
