package com.ngleanhvu.user_service.service;

import com.ngleanhvu.user_service.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    User getUserById(Long userId);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
}
