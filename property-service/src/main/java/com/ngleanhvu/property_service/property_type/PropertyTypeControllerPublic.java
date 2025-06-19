package com.ngleanhvu.property_service.property_type;

import com.ngleanhvu.property_service.property_type.entity.PropertyType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/property-types/public")
public class PropertyTypeControllerPublic {

    private final PropertyTypeService propertyTypeService;

    @GetMapping
    public ResponseEntity<List<PropertyType>> getPropertyTypes() {
        return ResponseEntity.ok(propertyTypeService.getPropertyTypes());
    }
}
