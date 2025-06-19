package com.ngleanhvu.location_service.district;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngleanhvu.common.response.ApiResponse;
import com.ngleanhvu.location_service.district.entity.District;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/districts")
@RequiredArgsConstructor
public class DistrictController {
    private final DistrictService districtService;

    @GetMapping("/cities/{cityId}")
    public ResponseEntity<?> getDistrictsByCityId(@PathVariable int cityId) throws JsonProcessingException {
        return ApiResponse.success("Get district by city id success", districtService.getDistrictsByCityId(cityId));
    }
}
