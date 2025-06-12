package com.ngleanhvu.location_service.city;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.location_service.city.entity.City;
import com.ngleanhvu.location_service.city.redis.CityRedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final StringRedisTemplate stringRedisTemplate;
    private final ObjectMapper objectMapper;

    @Value("${redis.time-out}")
    private int redisTimeOut;

    @Override
    public List<City> getCitiesByCountryId(int countryId) throws JsonProcessingException {
        List<City> cities;
        String cityKey = CityRedisUtil.generateCityByCountryId(countryId);

        String cityJson = stringRedisTemplate.opsForValue().get(cityKey);
        if (cityJson != null && !cityJson.isEmpty()) {
            cities = objectMapper.readValue(cityJson, new TypeReference<>() {});
        } else {
            cities = cityRepository.findCitiesByCountryId(countryId);
            cityJson = objectMapper.writeValueAsString(cities);
            stringRedisTemplate.opsForValue().set(cityKey, cityJson, redisTimeOut, TimeUnit.MINUTES);
        }

        return cities;
    }

    @Override
    public City getCityById(int id) throws JsonProcessingException {
        City city;
        String cityKey = CityRedisUtil.generateCityByCityId(id);

        String cityJson = stringRedisTemplate.opsForValue().get(cityKey);
        if (cityJson != null && !cityJson.isEmpty()) {
            city = objectMapper.readValue(cityJson, City.class);
        } else {
            city = cityRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("City","id",String.valueOf(id)));
            cityJson = objectMapper.writeValueAsString(city);
            stringRedisTemplate.opsForValue().set(cityKey, cityJson, redisTimeOut, TimeUnit.MINUTES);
        }

        return city;
    }


}
