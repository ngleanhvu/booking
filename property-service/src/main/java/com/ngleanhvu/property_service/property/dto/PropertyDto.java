package com.ngleanhvu.property_service.property.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class PropertyDto {

    @NotBlank // Dành cho String
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Integer propertyTypeId;

    @NotNull
    private Integer roomTypeId;

    @NotEmpty // Map không được rỗng
    private Map<String, String> amenities = new HashMap<>();

    @NotBlank
    private String addressStreet;

    @NotNull
    private Integer cityId;

    @NotNull
    private Integer countryId;

    @NotNull
    private Integer wardId;

    @NotNull
    private Integer districtId;

    @NotBlank
    private String postalCode;

    @NotEmpty
    private List<MultipartFile> images;

    @NotNull
    private BigDecimal latitude;

    @NotNull
    private BigDecimal longitude;

    @NotNull
    private BigDecimal pricePerNight;

    @NotBlank
    private String currencyCode;

    @NotNull
    private Integer maxGuests;

    @NotNull
    private Integer numBedrooms;

    @NotNull
    private Integer numBeds;

    @NotNull
    private BigDecimal numBathrooms;
}
