package com.ngleanhvu.property_service;

import com.ngleanhvu.common.proto.City;
import com.ngleanhvu.common.proto.CityList;
import com.ngleanhvu.property_service.grpc.client.CityGrpcClient;
import com.ngleanhvu.property_service.property.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class Test {

    private final CityGrpcClient cityGrpcClient;

    @GetMapping
    public List<String> test() {
        CityList cityList = cityGrpcClient.getCity(1);
        return cityList.getCitiesList().stream().map(City::getName).toList();
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<String> getCityById(@PathVariable("id") Integer id) {
        City city = cityGrpcClient.getCityById(id);
        return ResponseEntity.ok(city.getName());
    }
}
