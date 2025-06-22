package com.ngleanhvu.auth_service.controller;

import com.ngleanhvu.auth_service.dto.*;
import com.ngleanhvu.auth_service.service.AuthService;
import com.ngleanhvu.auth_service.util.JwtUtil;
import com.ngleanhvu.common.async.ResponseFutureManager;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

        String userId = ResponseFutureManager.createFuture();
        CompletableFuture<String> future = ResponseFutureManager.getFuture(userId);

        try {
            authService.register(registerDto, userId);

            String result = future.get(20, TimeUnit.SECONDS);

            if ("SUCCESS".equals(result)) {
                return ResponseEntity.ok(new ApiResponse<>("Create new user success",
                        HttpStatus.CREATED.name(),
                        null));
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ApiResponse<>(result,
                                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                                null));
            }
        } catch (TimeoutException ex) {
            return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT)
                    .body(new ApiResponse<>("Timeout", "TIMEOUT", null));
        } catch (InvalidResourceException | ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name(), null));
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            ResponseFutureManager.remove(userId);
        }

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
