package com.ngleanhvu.common.dto;

import lombok.Data;

@Data
public class UserCreationSuccessEvent {
    private String userId;
    private String status;
}
