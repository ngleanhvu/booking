package com.ngleanhvu.property_service.property_amenity;

import com.ngleanhvu.property_service.property_amenity.entity.PropertyAmenity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/property-amenities/public")
public class PropertyAmenityControllerPublic {

    private final PropertyAmenityService propertyAmenityService;

    @GetMapping
    public ResponseEntity<List<PropertyAmenity>> getPropertyAmenities() {
        return ResponseEntity.ok(propertyAmenityService.getPropertyAmenities());
    }
}
