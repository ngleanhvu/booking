package com.ngleanhvu.property_service.property_amenity;

import com.ngleanhvu.property_service.property_amenity.entity.PropertyAmenity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyAmenityRepository extends JpaRepository<PropertyAmenity, Integer> {
}
