package com.ngleanhvu.property_service.property_type;

import com.ngleanhvu.property_service.property_type.entity.PropertyType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyTypeRepository extends JpaRepository<PropertyType, Integer> {
}
