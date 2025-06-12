package com.ngleanhvu.location_service.ward.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ngleanhvu.location_service.district.entity.District;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ward")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Ward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "district_id", nullable = false)
    @JsonIgnore
    private District district;
}

