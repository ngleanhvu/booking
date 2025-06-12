package com.ngleanhvu.location_service.district;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngleanhvu.location_service.district.entity.District;
import com.ngleanhvu.location_service.district.redis.DistrictRedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;
    private final ObjectMapper objectMapper;
    private final StringRedisTemplate stringRedisTemplate;

    @Value("${redis.time-out}")
    private int redisTimeOut;

    @Override
    public List<District> getDistrictsByCityId(int cityId) throws JsonProcessingException {
        List<District> districts;
        String districtKey = DistrictRedisUtil.generateDistrictByCityId(cityId);

        String districtJson = stringRedisTemplate.opsForValue().get(districtKey);

        if (districtJson != null && !districtJson.isEmpty()) {
            districts = objectMapper.readValue(districtJson, new TypeReference<>() {});
        } else {
            districts = districtRepository.findDistrictsByCity(cityId);
            districtJson = objectMapper.writeValueAsString(districts);
            stringRedisTemplate.opsForValue().set(districtKey, districtJson, redisTimeOut, TimeUnit.MINUTES);
        }

        return districts;
    }
}
