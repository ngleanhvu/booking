package com.ngleanhvu.property_service.property.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class PropertyDto {
    private String title;
    private String description;
    private Integer propertyTypeId;
    private Integer roomTypeId;
    private String addressStreet;
    private Integer cityId;
    private Integer countryId;
    private Integer wardId;
    private Integer districtId;
    private String postalCode;
    private List<String> images;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal pricePerNight;
    private String currencyCode;
    private Integer maxGuests;
    private Integer numBedrooms;
    private Integer numBeds;
    private BigDecimal numBathrooms;
}
