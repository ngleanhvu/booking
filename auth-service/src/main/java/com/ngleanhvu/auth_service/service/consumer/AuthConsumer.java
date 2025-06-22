package com.ngleanhvu.auth_service.service.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngleanhvu.auth_service.repository.AuthRepository;
import com.ngleanhvu.common.async.ResponseFutureManager;
import com.ngleanhvu.common.constant.KafkaConst;
import com.ngleanhvu.common.dto.UserCreationFailedEvent;
import com.ngleanhvu.common.dto.UserCreationSuccessEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthConsumer {

    private final ObjectMapper objectMapper;
    private final AuthRepository authRepository;

    @Transactional
    @KafkaListener(topics = KafkaConst.USER_REGISTER_FAILURE_TOPIC, groupId = KafkaConst.AUTH_GROUP_ID)
    public void userRegisterFailure(String message) throws JsonProcessingException {
        UserCreationFailedEvent event = objectMapper.readValue(message, UserCreationFailedEvent.class);
        CompletableFuture<String> future = ResponseFutureManager.getFuture(event.getAuthId());
        authRepository.deleteById(event.getAuthId());
        log.info("Delete auth with id {} success", event.getAuthId());
        if (future != null) {
            future.complete(event.getAuthId());
        }
    }

    @KafkaListener(topics = KafkaConst.USER_REGISTER_SUCCESS_TOPIC, groupId = KafkaConst.AUTH_GROUP_ID)
    public void userRegisterSuccess(String message) throws JsonProcessingException {
        UserCreationSuccessEvent event = objectMapper.readValue(message, UserCreationSuccessEvent.class);
        CompletableFuture<String> future = ResponseFutureManager.getFuture(event.getUserId());
        if (future != null) {
            future.complete(event.getStatus());
        }
    }
}
