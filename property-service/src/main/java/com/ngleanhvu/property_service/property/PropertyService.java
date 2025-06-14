package com.ngleanhvu.property_service.property;

import com.ngleanhvu.property_service.property.dto.PropertyDto;
import java.io.IOException;

public interface PropertyService {
    void createProperty(PropertyDto propertyDto) throws IOException;
}
