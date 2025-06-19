package com.ngleanhvu.location_service.ward;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngleanhvu.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/wards/public")
@RequiredArgsConstructor
public class WardControllerPublic {
    private final WardService wardService;

    @GetMapping("/districts/{districtId}")
    public ResponseEntity<?> getDistrictsByCityId(@PathVariable("districtId") int districtId) throws JsonProcessingException {
        return ApiResponse.success("Get list ward by district id success", this.wardService.getWardsByDistrict(districtId));
    }
}
