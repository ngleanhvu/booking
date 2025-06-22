package com.ngleanhvu.auth_service.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@NotNull
public class LogoutRequest {
    private String refreshToken;
}
