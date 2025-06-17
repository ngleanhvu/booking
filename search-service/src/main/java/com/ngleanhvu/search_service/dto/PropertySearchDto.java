package com.ngleanhvu.search_service.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class PropertySearchDto {
    private Integer country;
    private Integer city;
    private Integer district;
    private Integer ward;
    private String title;
    private String description;
    private Integer propertyType;
    private Integer roomType;
    private BigDecimal price;
    private Integer numBeds;
    private Integer numBaths;
}
