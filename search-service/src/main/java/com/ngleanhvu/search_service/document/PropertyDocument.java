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

    @Field(type = FieldType.Object)
    private PropertyType propertyType;

    @Field(type = FieldType.Object)
    private RoomType roomType;

    @Field(type = FieldType.Keyword)
    private String addressStreet;

    @Field(type = FieldType.Object)
    private City city;

    @Field(type = FieldType.Object)
    private Country country;

    @Field(type = FieldType.Object)
    private Ward ward;

    @Field(type = FieldType.Object)
    private District district;

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
        private double lat;
        private double lon;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RoomType {
        private Integer id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PropertyType {
        private Integer id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Country {
        private Integer id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class City {
        private Integer id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class District {
        private Integer id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Ward {
        private Integer id;
        private String name;
    }
}
