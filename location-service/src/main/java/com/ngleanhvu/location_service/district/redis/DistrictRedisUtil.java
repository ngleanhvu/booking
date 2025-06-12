package com.ngleanhvu.location_service.district.redis;

public class DistrictRedisUtil {
    public static String generateDistrictByCityId(int cityId) {
        return "district:city:" + cityId;
    }
}
