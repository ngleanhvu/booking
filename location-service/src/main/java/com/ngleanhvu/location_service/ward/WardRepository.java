package com.ngleanhvu.location_service.ward;

import com.ngleanhvu.location_service.ward.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Integer> {
    @Query(value = "SELECT w FROM Ward w WHERE w.district.id = :districtId")
    List<Ward> findWardsByDistrictId(@Param("districtId") int districtId);
}
