package com.ngleanhvu.location_service.country;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngleanhvu.location_service.country.entity.Country;

import java.util.List;
import java.util.Map;

public interface CountryService {
    List<Country> getCountries(Map<String, String> params);
    List<Country> findAllCountries() throws JsonProcessingException;
    Country findCountryById(int id) throws JsonProcessingException;
    Country findCountryByName(String name);
}
