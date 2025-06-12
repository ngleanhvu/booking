package com.ngleanhvu.location_service.country;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ngleanhvu.location_service.country.entity.Country;
import com.ngleanhvu.location_service.country.redis.CountryRedisUtil;
import com.ngleanhvu.location_service.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final StringRedisTemplate redisTemplate;
    private final ObjectMapper objectMapper;

    @Value("${page.size}")
    private int pageSize;

    @Override
    public List<Country> getCountries(Map<String, String> params) {
        String name = params.get("name") != null ? params.get("name") : "";
        Sort sort = Sort.by(Sort.Direction.ASC, params.get("sort") == null ? "name" : params.get("sort"));
        int page = Integer.parseInt(params.get("page") != null ? params.get("page") : "1");
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<Country> pageCountry = countryRepository.findAll(name, pageable);
        return pageCountry.getContent();
    }

    @Override
    public List<Country> findAllCountries() throws JsonProcessingException {
        List<Country> countries;
        String countryKey = CountryRedisUtil.generateAllCountriesKey();

        String countryJson = redisTemplate.opsForValue().get(countryKey);
        if (countryJson != null && !countryJson.isEmpty()) {
            countries = objectMapper.readValue(countryJson, new TypeReference<>() {
            });
        } else {
            countries = countryRepository.findAll();
            countryJson = objectMapper.writeValueAsString(countries);
            redisTemplate.opsForValue().set(countryKey, countryJson);
        }

        return countries;
    }


    @Override
    public Country findCountryById(int id) throws JsonProcessingException {
        Country country;
        String countryKey = CountryRedisUtil.generateCountryKeyById(id);
        String countryJson = redisTemplate.opsForValue().get(countryKey);

        if (countryJson != null && !countryJson.isEmpty()) {
            country = objectMapper.readValue(countryJson, new TypeReference<>() {});
        } else {
            country = countryRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Country", "id", String.valueOf(id)));
            countryJson = objectMapper.writeValueAsString(country);
            redisTemplate.opsForValue().set(countryKey, countryJson);
        }

        return country;
    }

    @Override
    public Country findCountryByName(String name) {
        Optional<Country> optionalCountry = countryRepository.findCountryByName(name);
        if (optionalCountry.isEmpty())
            throw new NotFoundException("Country","name", name);
        return optionalCountry.get();
    }

}
