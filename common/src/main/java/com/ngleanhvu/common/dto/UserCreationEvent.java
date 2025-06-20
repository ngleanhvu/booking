package com.ngleanhvu.common.dto;

import lombok.Data;

@Data
public class UserCreationEvent {
    private String id;
    private String email;
    private String phone;
    private String fullName;
    private String avatarUrl;
}
