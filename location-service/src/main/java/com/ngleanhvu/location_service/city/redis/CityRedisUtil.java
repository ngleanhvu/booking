package com.ngleanhvu.location_service.city.redis;

public class CityRedisUtil {
    public static String generateCityByCountryId(int countryId) {
        return "city:country:" + countryId;
    }
    public static String generateCityByCityId(int cityId) {return "city:"+cityId;}
}
