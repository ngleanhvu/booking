package com.ngleanhvu.auth_service.controller;

import com.ngleanhvu.auth_service.dto.*;
import com.ngleanhvu.auth_service.service.AuthService;
import com.ngleanhvu.auth_service.util.JwtUtil;
import com.ngleanhvu.common.exception.InvalidResourceException;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final JwtUtil jwtUtil;
    private final AuthService authService;

    @GetMapping("/verify")
    public ResponseEntity<Void> verifyToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = authHeader.replace("Bearer ", "");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/register", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<ApiResponse<Void>> register(@Valid @ModelAttribute RegisterDto registerDto) throws IOException {

        authService.register(registerDto);
        ApiResponse<Void> apiResponse = new ApiResponse<>("Create user success",
                                                            HttpStatus.CREATED.name(), null);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        LoginResponse loginResponse = authService.login(loginRequest);
        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>("Login success",
                HttpStatus.OK.name(), loginResponse);
        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<Void>> logout(@Valid @RequestBody LogoutRequest logoutRequest) throws InvalidResourceException, ResourceNotFoundException {
        authService.logout(logoutRequest.getRefreshToken());
        ApiResponse<Void> response = new ApiResponse<>("Logout success",
                HttpStatus.OK.name(), null);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<String>> refresh(@Valid @RequestBody RefreshRequest refreshRequest) throws InvalidResourceException, ResourceNotFoundException {
        String accessToken = authService.refresh(refreshRequest.getRefreshToken());
        ApiResponse<String> apiResponse = new ApiResponse<>("Refresh token success",
                HttpStatus.OK.name(), accessToken);
        return ResponseEntity.ok(apiResponse);
    }

}
