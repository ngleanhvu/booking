package com.ngleanhvu.location_service.country.redis;

public class CountryRedisUtil {
    public static String generateCountryKeyById(int countryId) {
        return "country:" + countryId;
    }

    public static String generateAllCountriesKey() {
        return "countries";
    }
}
