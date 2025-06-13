package com.ngleanhvu.property_service.property_amenity.entity;

import com.ngleanhvu.property_service.property.entity.Property;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name = "property_amenity_link")
public class PropertyAmenityLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "property_id")
    @ManyToOne
    private Property property;

    @JoinColumn(name = "property_amenity_id")
    @ManyToOne
    private PropertyAmenity propertyAmenity;

    private int number;
}
