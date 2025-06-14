package com.ngleanhvu.search_service.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PropertySearchDto {
    private String country;
    private String city;
    private String district;
    private String ward;
    private String string;
    private String title;
    private String description;
    private String propertyType;
    private String roomType;
    private BigDecimal price;
    private Integer numBeds;
    private Integer numBaths;
}
