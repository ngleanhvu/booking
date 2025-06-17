package com.ngleanhvu.user_service.service.impl;

import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.user_service.entity.User;
import com.ngleanhvu.user_service.repo.UserRepo;
import com.ngleanhvu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public User getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", String.valueOf(userId)));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "email", String.valueOf(email)));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "username", username));
    }
}
