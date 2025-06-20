package com.ngleanhvu.user_service.service;

import com.ngleanhvu.common.dto.UserCreationEvent;
import com.ngleanhvu.user_service.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUserById(String userId);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    void createUser(String message);
}
