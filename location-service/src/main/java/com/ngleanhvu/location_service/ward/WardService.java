package com.ngleanhvu.location_service.ward;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngleanhvu.location_service.ward.entity.Ward;

import java.util.List;

public interface WardService {
    List<Ward> getWardsByDistrict(int districtId) throws JsonProcessingException;
}
