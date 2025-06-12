package com.ngleanhvu.location_service.ward.redis;

public class WardRedisUtil {
    public static String generateWardKeyByDistrictId(int districtId) {
        return "ward:district:" + districtId;
    }
}
