package com.ngleanhvu.search_service.document;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Document(indexName = "property_index")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDocument {

    @Id
    private Integer id;

    @Field(type = FieldType.Text)
    private String title;

    @Field(type = FieldType.Text)
    private String description;

    @Field(type = FieldType.Integer)
    private Integer propertyType;

    @Field(type = FieldType.Integer)
    private Integer roomType;

    @Field(type = FieldType.Keyword)
    private String addressStreet;

    @Field(type = FieldType.Integer)
    private Integer city;

    @Field(type = FieldType.Integer)
    private Integer country;

    @Field(type = FieldType.Integer)
    private Integer ward;

    @Field(type = FieldType.Integer)
    private Integer district;

    @Field(type = FieldType.Keyword)
    private String postalCode;

    @Field(type = FieldType.Keyword)
    private String thumbnail;

    @Field(type = FieldType.Keyword)
    private List<String> images;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Double)
    private BigDecimal pricePerNight;

    @Field(type = FieldType.Keyword)
    private String currencyCode;

    @Field(type = FieldType.Integer)
    private Integer maxGuests;

    @Field(type = FieldType.Integer)
    private Integer numBedrooms;

    @Field(type = FieldType.Integer)
    private Integer numBeds;

    @Field(type = FieldType.Double)
    private BigDecimal numBathrooms;

    @Field(type = FieldType.Boolean)
    private Boolean active;

    // inner class hoặc sử dụng class sẵn có
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GeoPoint {
        private BigDecimal lat;
        private BigDecimal lon;
    }
}
