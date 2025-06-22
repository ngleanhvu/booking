package com.ngleanhvu.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NotNull
@NoArgsConstructor
@AllArgsConstructor
public class UserCreationFailedEvent {
    private String authId;
    private String reason;
}
