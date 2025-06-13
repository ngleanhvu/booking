package com.ngleanhvu.property_service.property;

import com.ngleanhvu.property_service.property.dto.PropertyDto;
import com.ngleanhvu.property_service.property.entity.Property;
import com.ngleanhvu.property_service.room_type.entity.RoomType;

import java.io.IOException;
import java.util.List;

public interface PropertyService {
    void createProperty(PropertyDto propertyDto) throws IOException;
}
