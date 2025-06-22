package com.ngleanhvu.user_service.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngleanhvu.common.async.ResponseFutureManager;
import com.ngleanhvu.common.constant.KafkaConst;
import com.ngleanhvu.common.dto.MailRecord;
import com.ngleanhvu.common.dto.UserCreationEvent;
import com.ngleanhvu.common.dto.UserCreationFailedEvent;
import com.ngleanhvu.common.dto.UserCreationSuccessEvent;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.user_service.entity.User;
import com.ngleanhvu.user_service.repo.UserRepo;
import com.ngleanhvu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public User getUserById(String userId) {
        return userRepo.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User", "id", userId));
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

    @KafkaListener(topics = KafkaConst.USER_CREATED_TOPIC, groupId = KafkaConst.USER_GROUP_ID)
    @Override
    public void createUser(String message) throws JsonProcessingException {
        UserCreationEvent userCreationEvent = objectMapper.readValue(message, UserCreationEvent.class);
        User user = new User();
        user.setEmail(userCreationEvent.getEmail());
        user.setId(userCreationEvent.getId());
        user.setPhone(userCreationEvent.getPhone());
        user.setAvatar(userCreationEvent.getAvatarUrl());
        user.setFullName(userCreationEvent.getFullName());

        int idx = userCreationEvent.getEmail().indexOf("@");
        String username = userCreationEvent.getEmail().substring(0, idx);
        user.setUsername(username);

        try {
            userRepo.save(user);

            MailRecord mailRecord = new MailRecord(user.getEmail(),
                    "Email verify for registered user",
                    "Welcome!.");
            kafkaTemplate.send(KafkaConst.MAIL_TOPIC, user.getId(), objectMapper.writeValueAsString(mailRecord));
            UserCreationSuccessEvent userCreationSuccessEvent = new UserCreationSuccessEvent();
            userCreationSuccessEvent.setUserId(user.getId());
            userCreationSuccessEvent.setStatus("SUCCESS");
            kafkaTemplate.send(KafkaConst.USER_REGISTER_SUCCESS_TOPIC, user.getId(), objectMapper.writeValueAsString(userCreationSuccessEvent));
        } catch (Exception ex) {
            log.error("Failed to save user: {}", ex.getMessage());
            UserCreationFailedEvent userCreationFailedEvent = new UserCreationFailedEvent();
            userCreationFailedEvent.setAuthId(user.getId());
            userCreationFailedEvent.setReason(ex.getMessage());
            kafkaTemplate.send(KafkaConst.USER_REGISTER_FAILURE_TOPIC, user.getId(),
                    objectMapper.writeValueAsString(userCreationFailedEvent));
        }
    }


}
