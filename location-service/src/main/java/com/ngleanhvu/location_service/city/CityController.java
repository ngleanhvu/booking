package com.ngleanhvu.location_service.city;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.location_service.city.entity.City;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cities")
public class CityController {

    private final CityService cityService;

    @GetMapping("/countries/{countryId}")
    public ResponseEntity<List<City>> getCityByCountryId(@PathVariable int countryId) throws JsonProcessingException {
        return ResponseEntity.ok(cityService.getCitiesByCountryId(countryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable int id) throws JsonProcessingException {
        return ResponseEntity.ok(cityService.getCityById(id));
    }
}
