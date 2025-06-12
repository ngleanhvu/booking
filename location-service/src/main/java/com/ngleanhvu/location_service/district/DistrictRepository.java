package com.ngleanhvu.location_service.district;

import com.ngleanhvu.location_service.city.entity.City;
import com.ngleanhvu.location_service.district.entity.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    @Query(value = "SELECT d FROM District d WHERE d.city.id = :cityId")
    List<District> findDistrictsByCity(@Param("cityId") int cityId);
}
