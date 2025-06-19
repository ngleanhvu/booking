package com.ngleanhvu.location_service.country;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngleanhvu.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries/public")
public class CountryControllerPublic {

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<?> getCountries(@RequestParam Map<String, String> params) {
        return ApiResponse.success("Get list country success", countryService.getCountries(params));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCountries() throws JsonProcessingException {
        return ApiResponse.success("Get all country success", countryService.findAllCountries());
    }
}
