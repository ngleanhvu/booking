package com.ngleanhvu.location_service.country;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngleanhvu.location_service.country.entity.Country;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<Country>> getCountries(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(countryService.getCountries(params));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Country>> getAllCountries() throws JsonProcessingException {
        return ResponseEntity.ok(countryService.findAllCountries());
    }
}
