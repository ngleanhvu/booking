package com.ngleanhvu.auth_service.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngleanhvu.auth_service.dto.LoginRequest;
import com.ngleanhvu.auth_service.dto.LoginResponse;
import com.ngleanhvu.auth_service.dto.RegisterDto;
import com.ngleanhvu.auth_service.entity.Auth;
import com.ngleanhvu.auth_service.entity.Role;
import com.ngleanhvu.auth_service.repository.AuthRepository;
import com.ngleanhvu.auth_service.service.AuthService;
import com.ngleanhvu.auth_service.util.JwtUtil;
import com.ngleanhvu.auth_service.util.KeyUtil;
import com.ngleanhvu.common.constant.KafkaConst;
import com.ngleanhvu.common.dto.UserCreationEvent;
import com.ngleanhvu.common.exception.InvalidResourceException;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.common.grpc_client.UserGrpcClient;
import com.ngleanhvu.common.proto.User;
import com.ngleanhvu.common.upload.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthRepository authRepository;
    private final UserGrpcClient userGrpcClient;
    private final PasswordEncoder passwordEncoder;
    private final S3Service s3Service;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;
    private final RedisTemplate<String, Object> redisTemplate;


    @Value("${jwt.expiration.refresh_token}")
    private long EXPIRATION_REFRESH_TOKEN;

    @Override
    @Transactional
    public void register(RegisterDto registerDto) throws ResourceNotFoundException, InvalidResourceException, IOException {
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

    @Override
    public LoginResponse login(LoginRequest loginRequest) throws ResourceNotFoundException, InvalidResourceException {
        Auth auth = authRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Auth","email", loginRequest.getEmail()));

        if (!passwordEncoder.matches(loginRequest.getPassword(), auth.getPassword())) {
            throw new InvalidResourceException("Password does not match");
        }

        String accessToken = jwtUtil.generateAccessToken(auth);
        String refreshToken = jwtUtil.generateRefreshToken(auth);

        String jti = jwtUtil.getJti(refreshToken)
                .orElseThrow(() -> new InvalidResourceException("Refresh token does not exist"));

        Date expiration = jwtUtil.getExpiration(refreshToken)
                        .orElseThrow(() -> new InvalidResourceException("Refresh token does not exist"));

        redisTemplate.opsForValue().set(KeyUtil.generateRefreshKey(auth.getId(), jti), expiration.getTime(), EXPIRATION_REFRESH_TOKEN, TimeUnit.SECONDS);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(accessToken);
        loginResponse.setRefreshToken(refreshToken);

        return loginResponse;
    }

    @Override
    public void logout(String refreshToken) {
        String jti = jwtUtil.getJti(refreshToken)
                .orElseThrow(() -> new InvalidResourceException("Refresh token does not exist"));

        String authId = jwtUtil.getSubject(refreshToken)
                .orElseThrow(() -> new InvalidResourceException("Refresh token does not exist"));

        Date expiration = jwtUtil.getExpiration(refreshToken)
                .orElseThrow(() -> new InvalidResourceException("Refresh token does not exist"));

        long ttl = expiration.getTime() - System.currentTimeMillis() > 0 ? expiration.getTime() : 0;

        String key = KeyUtil.generateRefreshKey(authId, jti);

        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
            redisTemplate.opsForValue().set(KeyUtil.blackListKey(jti), 1, ttl, TimeUnit.SECONDS);
        }
    }

    @Override
    public String refresh(String refreshToken)  {

        String jti = jwtUtil.getJti(refreshToken)
                .orElseThrow(() -> new InvalidResourceException("Refresh token does not exist"));

        String blackListKey = KeyUtil.blackListKey(jti);

        if (redisTemplate.hasKey(blackListKey)) {
            throw new InvalidResourceException("Refresh revoked");
        }

        String authId = jwtUtil.getSubject(refreshToken)
                .orElseThrow(() -> new InvalidResourceException("Refresh token does not exist"));

        Auth auth = authRepository.findById(authId)
                .orElseThrow(() -> new ResourceNotFoundException("Auth","id", authId));

        return jwtUtil.generateAccessToken(auth);
    }


}
