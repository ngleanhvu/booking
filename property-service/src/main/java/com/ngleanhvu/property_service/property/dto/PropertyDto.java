package com.ngleanhvu.property_service.property.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.apache.hc.client5.http.entity.mime.MultipartPart;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
@NotNull
@NotEmpty
public class PropertyDto {
    private String title;
    private String description;
    private Integer propertyTypeId;
    private Integer roomTypeId;
    private List<Integer> amenityIds;
    private String addressStreet;
    private Integer cityId;
    private Integer countryId;
    private Integer wardId;
    private Integer districtId;
    private String postalCode;
    private List<MultipartFile> images;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal pricePerNight;
    private String currencyCode;
    private Integer maxGuests;
    private Integer numBedrooms;
    private Integer numBeds;
    private BigDecimal numBathrooms;
}
