package com.ngleanhvu.user_service.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngleanhvu.common.constant.KafkaConst;
import com.ngleanhvu.common.dto.UserCreationEvent;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.user_service.entity.User;
import com.ngleanhvu.user_service.repo.UserRepo;
import com.ngleanhvu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ObjectMapper objectMapper;

    @Override
    public User getUserById(String userId) {
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

    @Transactional
    @KafkaListener(topics = KafkaConst.USER_CREATED_TOPIC, groupId = KafkaConst.USER_GROUP_ID)
    @Override
    public void createUser(String message) {
        UserCreationEvent userCreationEvent = objectMapper.convertValue(message, UserCreationEvent.class);
        User user = new User();
        user.setEmail(userCreationEvent.getEmail());
        user.setId(userCreationEvent.getId());
        user.setPhone(userCreationEvent.getPhone());
        user.setUsername(userCreationEvent.getEmail());
        user.setAvatar(userCreationEvent.getAvatarUrl());

        int idx = userCreationEvent.getEmail().indexOf("@");
        String username = userCreationEvent.getEmail().substring(0, idx);
        user.setUsername(username);

        userRepo.save(user);
    }


}
