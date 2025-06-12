package com.ngleanhvu.property_service.property;

import com.ngleanhvu.property_service.property.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
}
