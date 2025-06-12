package com.ngleanhvu.location_service.country.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "country")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 2, unique = true)
    private String code;

    @Column(length = 100)
    private String name;
}
