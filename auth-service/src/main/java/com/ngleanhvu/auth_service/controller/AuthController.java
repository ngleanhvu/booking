package com.ngleanhvu.auth_service.controller;

import com.ngleanhvu.auth_service.dto.RegisterDto;
import com.ngleanhvu.auth_service.service.AuthService;
import com.ngleanhvu.auth_service.util.JwtUtil;
import com.ngleanhvu.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

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
    public ResponseEntity<ApiResponse<Void>> register(@ModelAttribute RegisterDto registerDto) throws IOException {

        authService.register(registerDto);
        ApiResponse<Void> apiResponse = new ApiResponse<>("Create user success",
                                                            HttpStatus.CREATED.name(), null);
        return ResponseEntity.ok(apiResponse);
    }

}
