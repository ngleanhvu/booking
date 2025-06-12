package com.ngleanhvu.location_service.district;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngleanhvu.location_service.district.entity.District;

import java.util.List;

public interface DistrictService {
    List<District> getDistrictsByCityId(int cityId) throws JsonProcessingException;
}
