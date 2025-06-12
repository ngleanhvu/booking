package com.ngleanhvu.location_service.city;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngleanhvu.location_service.city.entity.City;

import java.util.List;

public interface CityService {
    List<City> getCitiesByCountryId(int countryId) throws JsonProcessingException;
    City getCityById(int id) throws JsonProcessingException;
}
