package com.ngleanhvu.property_service.property;

import com.ngleanhvu.common.constant.VariableConst;
import com.ngleanhvu.common.exception.InvalidResourceException;
import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.common.proto.*;
import com.ngleanhvu.common.upload.S3Service;
import com.ngleanhvu.common.grpc_client.CityGrpcClient;
import com.ngleanhvu.common.grpc_client.CountryGrpcClient;
import com.ngleanhvu.common.grpc_client.DistrictGrpcClient;
import com.ngleanhvu.common.grpc_client.WardGrpcClient;
import com.ngleanhvu.property_service.property.dto.PropertyDto;
import com.ngleanhvu.property_service.property.entity.CurrentCode;
import com.ngleanhvu.property_service.property.entity.Property;
import com.ngleanhvu.property_service.property_amenity.PropertyAmenityLinkRepository;
import com.ngleanhvu.property_service.property_amenity.PropertyAmenityRepository;
import com.ngleanhvu.property_service.property_amenity.entity.PropertyAmenity;
import com.ngleanhvu.property_service.property_amenity.entity.PropertyAmenityLink;
import com.ngleanhvu.property_service.property_type.PropertyTypeRepository;
import com.ngleanhvu.property_service.property_type.entity.PropertyType;
import com.ngleanhvu.property_service.room_type.RoomTypeRepository;
import com.ngleanhvu.property_service.room_type.entity.RoomType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
    private final S3Service s3Service;
    private final PropertyAmenityLinkRepository propertyAmenityLinkRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void createProperty(PropertyDto propertyDto)  {

        Country countryProto = this.countryGrpcClient.getCountryById(propertyDto.getCountryId());
        if (countryProto == null) {
            throw new ResourceNotFoundException("Country","id",String.valueOf(propertyDto.getCountryId()));
        }

        City cityProto = this.cityGrpcClient.getCityById(propertyDto.getCityId());
        if (cityProto == null) {
            throw new ResourceNotFoundException("City","id",String.valueOf(propertyDto.getCityId()));
        }
        // Check city id is foreign key to country schema in location service
        if (cityProto.getCountryId() != countryProto.getId()) {
            throw new InvalidResourceException("City is not in this country");
        }

        District districtProto = this.districtGrpcClient.getDistrictById(propertyDto.getDistrictId());
        if (districtProto == null) {
            throw new ResourceNotFoundException("District","id",String.valueOf(propertyDto.getDistrictId()));
        }
        if (districtProto.getCityId() != cityProto.getId()) {
            throw new InvalidResourceException("District is not in this city");
        }

        Ward wardProto = this.wardGrpcClient.getWardById(propertyDto.getWardId());
        if (wardProto == null) {
            throw new ResourceNotFoundException("Ward","id",String.valueOf(propertyDto.getWardId()));
        }
        if (wardProto.getDistrictId() != districtProto.getId()) {
            throw new InvalidResourceException("Ward is not in this district");
        }

        RoomType roomType = this.roomTypeRepository.findById(propertyDto.getRoomTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Room Type","id",String.valueOf(propertyDto.getRoomTypeId())));

        PropertyType propertyType = this.propertyTypeRepository.findById(propertyDto.getPropertyTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("Property Type","id",String.valueOf(propertyDto.getPropertyTypeId())));


        Property property = new Property();
        property.setTitle(propertyDto.getTitle());
        property.setDescription(propertyDto.getDescription());
        property.setRoomType(roomType);
        property.setPropertyType(propertyType);
        property.setAddressStreet(propertyDto.getAddressStreet());
        property.setPostalCode(propertyDto.getPostalCode());
        property.setCountryId(propertyDto.getCountryId());
        property.setCityId(propertyDto.getCityId());
        property.setDistrictId(propertyDto.getDistrictId());
        property.setWardId(propertyDto.getWardId());
        property.setLatitude(propertyDto.getLatitude());
        property.setLongitude(propertyDto.getLongitude());
        property.setPricePerNight(propertyDto.getPricePerNight());
        property.setCurrencyCode(propertyDto.getCurrencyCode().equals("VND") ?
                CurrentCode.VND : CurrentCode.USD);
        property.setMaxGuests(propertyDto.getMaxGuests());
        property.setActive(true);
        property.setNumBathrooms(propertyDto.getNumBathrooms());
        property.setNumBedrooms(propertyDto.getNumBedrooms());
        property.setNumBeds(propertyDto.getNumBeds());

        List<String> urls = new ArrayList<>();
        if (propertyDto.getImages() != null && !propertyDto.getImages().isEmpty()) {
            if (propertyDto.getImages().size() > 10) {
                throw new IllegalArgumentException("Maximum 10 images allowed");
            }
            urls = propertyDto.getImages()
                    .parallelStream()
                    .filter(multipartFile ->
                            (Objects.equals(multipartFile.getContentType(), "image/jpeg")
                                    || Objects.equals(multipartFile.getContentType(), "image/png"))
                                    && multipartFile.getSize() <= VariableConst.imageSize
                    )
                    .map(multipartFile -> {
                        try {
                            return s3Service.uploadFile(multipartFile);
                        } catch (IOException e) {
                            throw new RuntimeException("Failed to upload file", e);
                        }
                    })
                    .toList();
        }
        property.setImages(urls); // image save as json format in db
        property.setThumbnail(urls.getFirst());

        Map<Integer, Integer> amenities = Optional.ofNullable(propertyDto.getAmenities())
                .orElse(Collections.emptyMap())
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        e -> Integer.parseInt(e.getKey()),
                        e -> Integer.parseInt(e.getValue())
                ));

        List<PropertyAmenity> propertyAmenities = this.propertyAmenityRepository
                .findAllById(amenities.keySet());

        List<PropertyAmenityLink>
                propertyAmenityLinks = new ArrayList<>();

        for (PropertyAmenity propertyAmenity: propertyAmenities) {
            PropertyAmenityLink
                    propertyAmenityLink = new PropertyAmenityLink();

            propertyAmenityLink.setProperty(property);
            propertyAmenityLink.setPropertyAmenity(propertyAmenity);
            propertyAmenityLink.setNumber(amenities.get(propertyAmenity.getId()));
            propertyAmenityLinks.add(propertyAmenityLink);
        }

        propertyRepository.saveAndFlush(property);
        propertyAmenityLinkRepository.saveAll(propertyAmenityLinks);
    }

}
