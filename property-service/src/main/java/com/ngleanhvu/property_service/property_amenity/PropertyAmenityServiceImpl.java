package com.ngleanhvu.property_service.property_amenity;

import com.ngleanhvu.property_service.property_amenity.entity.PropertyAmenity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyAmenityServiceImpl implements PropertyAmenityService {

    private final PropertyAmenityRepository propertyAmenityRepository;

    @Override
    public List<PropertyAmenity> getPropertyAmenities() {
        return propertyAmenityRepository.findAll();
    }
}
