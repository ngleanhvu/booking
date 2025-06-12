package com.ngleanhvu.location_service.ward;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngleanhvu.location_service.ward.entity.Ward;
import com.ngleanhvu.location_service.ward.redis.WardRedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;
    private final ObjectMapper objectMapper;
    private final StringRedisTemplate stringRedisTemplate;

    @Value("${redis.time-out}")
    private int redisTimeOut;

    @Override
    public List<Ward> getWardsByDistrict(int districtId) throws JsonProcessingException {
        List<Ward> wards;
        String wardKey = WardRedisUtil.generateWardKeyByDistrictId(districtId);

        String wardJson = stringRedisTemplate.opsForValue().get(wardKey);
        if (wardJson != null && !wardJson.isEmpty()) {
            wards = objectMapper.readValue(wardJson, new TypeReference<>() {});
        } else {
            wards = wardRepository.findWardsByDistrictId(districtId);
            wardJson = objectMapper.writeValueAsString(wards);
            stringRedisTemplate.opsForValue().set(wardKey, wardJson, redisTimeOut, TimeUnit.MINUTES);
        }

        return wards;
    }
}
