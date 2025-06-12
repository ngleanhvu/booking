package com.ngleanhvu.user_service.service.impl;

import com.ngleanhvu.user_service.entity.User;
import com.ngleanhvu.user_service.exception.UserNotFoundException;
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
                        new UserNotFoundException("User", "id", String.valueOf(userId)));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new UserNotFoundException("User", "email", email));
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElseThrow(() ->
                        new UserNotFoundException("User", "username", username));
    }
}
