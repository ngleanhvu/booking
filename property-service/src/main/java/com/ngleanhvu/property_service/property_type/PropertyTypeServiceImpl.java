package com.ngleanhvu.property_service.property_type;

import com.ngleanhvu.property_service.property_type.entity.PropertyType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyTypeServiceImpl implements PropertyTypeService {

    private final PropertyTypeRepository propertyTypeRepository;

    @Override
    public List<PropertyType> getPropertyTypes() {
        return propertyTypeRepository.findAll();
    }
}
