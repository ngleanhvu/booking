package com.ngleanhvu.property_service.property;

import com.ngleanhvu.common.proto.City;
import com.ngleanhvu.common.proto.WardServiceGrpc;
import com.ngleanhvu.property_service.grpc.client.CityGrpcClient;
import com.ngleanhvu.property_service.grpc.client.CountryGrpcClient;
import com.ngleanhvu.property_service.grpc.client.DistrictGrpcClient;
import com.ngleanhvu.property_service.grpc.client.WardGrpcClient;
import com.ngleanhvu.property_service.property.dto.PropertyDto;
import com.ngleanhvu.property_service.property_amenity.PropertyAmenityRepository;
import com.ngleanhvu.property_service.property_type.PropertyTypeRepository;
import com.ngleanhvu.property_service.room_type.RoomTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyTypeRepository propertyTypeRepository;
    private final PropertyAmenityRepository propertyAmenityRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final CityGrpcClient cityGrpcClient;
    private final CountryGrpcClient countryGrpcClient;
    private final WardGrpcClient wardGrpcClient;
    private final DistrictGrpcClient districtGrpcClient;

    @Override
    public void createProperty(PropertyDto propertyDto) {

    }
}
