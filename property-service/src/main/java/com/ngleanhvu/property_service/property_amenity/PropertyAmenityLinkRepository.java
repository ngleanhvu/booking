package com.ngleanhvu.property_service.property_amenity;

import com.ngleanhvu.property_service.property_amenity.entity.PropertyAmenity;
import com.ngleanhvu.property_service.property_amenity.entity.PropertyAmenityLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyAmenityLinkRepository extends JpaRepository<PropertyAmenityLink, Long> {
}
