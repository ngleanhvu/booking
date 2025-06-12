package com.ngleanhvu.location_service.city;

import com.ngleanhvu.location_service.city.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Integer> {
    @Query(value = "SELECT c FROM City c WHERE c.country.id = :countryId")
    List<City> findCitiesByCountryId(@Param("countryId") int countryId);
}
