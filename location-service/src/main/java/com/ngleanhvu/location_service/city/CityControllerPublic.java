package com.ngleanhvu.location_service.city;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngleanhvu.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cities/public")
public class CityControllerPublic {

    private final CityService cityService;

    @GetMapping("/countries/{countryId}")
    public ResponseEntity<?> getCityByCountryId(@PathVariable("countryId") int countryId) throws JsonProcessingException {
        return ApiResponse.success("Get list city by country id success", cityService.getCitiesByCountryId(countryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCityById(@PathVariable("id") int id) throws JsonProcessingException {
        return ApiResponse.success("Get city by id success", cityService.getCityById(id));
    }
}
