package com.ngleanhvu.auth_service.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngleanhvu.auth_service.dto.RegisterDto;
import com.ngleanhvu.auth_service.entity.Auth;
import com.ngleanhvu.auth_service.entity.Role;
import com.ngleanhvu.auth_service.repository.AuthRepository;
import com.ngleanhvu.auth_service.service.AuthService;
import com.ngleanhvu.common.constant.KafkaConst;
import com.ngleanhvu.common.dto.UserCreationEvent;
import com.ngleanhvu.common.exception.InvalidResourceException;
import com.ngleanhvu.common.grpc_client.UserGrpcClient;
import com.ngleanhvu.common.proto.User;
import com.ngleanhvu.common.upload.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final UserGrpcClient userGrpcClient;
    private final PasswordEncoder passwordEncoder;
    private final S3Service s3Service;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    @Override
    @Transactional
    public void register(RegisterDto registerDto) throws IOException {
        Optional<Auth> optionalEmail = authRepository.findByEmail(registerDto.getEmail());

        if (optionalEmail.isPresent()) {
            throw new InvalidResourceException("Email already exists");
        }

        User userProto = userGrpcClient.getUserByPhone(registerDto.getPhone());

        if (userProto == null) {
            throw new InvalidResourceException("Phone number exists");
        }

        if (!registerDto.getPassword().equals(registerDto.getConfirmPassword())) {
            throw new InvalidResourceException("Password does not match");
        }

        Auth auth = new Auth();
        String id = UUID.randomUUID().toString();
        auth.setId(id);
        auth.setEmail(registerDto.getEmail());
        auth.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        auth.setRole(Role.GUEST);

        authRepository.save(auth);
        String url = s3Service.uploadFile(registerDto.getAvatar());


        UserCreationEvent userCreationEvent = new UserCreationEvent();
        userCreationEvent.setId(id);
        userCreationEvent.setEmail(registerDto.getEmail());
        userCreationEvent.setPhone(registerDto.getPhone());
        userCreationEvent.setFullName(registerDto.getFullName());
        userCreationEvent.setAvatarUrl(url);

        String json = objectMapper.writeValueAsString(userCreationEvent);
        kafkaTemplate.send(KafkaConst.USER_CREATED_TOPIC, id, json);
    }
}
