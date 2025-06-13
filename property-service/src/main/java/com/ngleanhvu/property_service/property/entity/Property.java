package com.ngleanhvu.property_service.property.entity;

import com.ngleanhvu.common.converter.JsonListConverter;
import com.ngleanhvu.property_service.property_type.entity.PropertyType;
import com.ngleanhvu.property_service.room_type.entity.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "property")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "property_type_id", nullable = false)
    private PropertyType propertyType;

    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    private String addressStreet;

    @Column(nullable = false)
    private Integer cityId;

    @Column(nullable = false)
    private Integer countryId;

    private Integer wardId;
    private Integer districtId;

    @Column(length = 20)
    private String postalCode;

    private String thumbnail;

    @Convert(converter = JsonListConverter.class)
    @Column(columnDefinition = "json")
    private List<String> images;

    @Column(precision = 10, scale = 7)
    private BigDecimal latitude;

    @Column(precision = 10, scale = 7)
    private BigDecimal longitude;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal pricePerNight;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('VND', 'USD') DEFAULT 'VND'")
    private CurrentCode currencyCode = CurrentCode.VND;

    private Integer maxGuests = 1;
    private Integer numBedrooms = 1;
    private Integer numBeds = 1;

    @Column(precision = 2, scale = 1)
    private BigDecimal numBathrooms = new BigDecimal("1.0");

    private Boolean active = true;

}

