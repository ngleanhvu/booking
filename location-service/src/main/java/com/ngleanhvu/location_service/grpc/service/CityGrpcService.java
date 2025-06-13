package com.ngleanhvu.location_service.grpc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngleanhvu.common.proto.CityList;
import com.ngleanhvu.common.proto.CityRequest;
import com.ngleanhvu.common.proto.CityRequestForCountryId;
import com.ngleanhvu.location_service.city.CityService;
import com.ngleanhvu.location_service.city.entity.City;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import com.ngleanhvu.common.proto.CityServiceGrpc;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class CityGrpcService extends CityServiceGrpc.CityServiceImplBase {

    private final CityService cityService;

    @Override
    public void getAllCitiesByCountryId(CityRequestForCountryId request, StreamObserver<CityList> res) {
        int countryId = request.getCountryId();
        List<City> cities = null;
        try {
            cities = cityService.getCitiesByCountryId(countryId);
            System.out.println(cities.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<com.ngleanhvu.common.proto.City> proCities = cities.stream().map(city -> {
            com.ngleanhvu.common.proto.City c = com.ngleanhvu.common.proto.City.newBuilder()
                    .setId(city.getId())
                    .setName(city.getName())
                    .setCountryId(countryId)
                    .build();
            return c;
        }).toList();

        CityList cityList = CityList.newBuilder()
                .addAllCities(proCities)
                .build();


        res.onNext(cityList);
        res.onCompleted();
    }

    @Override
    public void getCityById(CityRequest request, StreamObserver<com.ngleanhvu.common.proto.City> streamObserver) {
        int cityId = request.getCityId();
        City city;
        try {
            city = cityService.getCityById(cityId);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        com.ngleanhvu.common.proto.City protoCity = com.ngleanhvu.common.proto.City.newBuilder()
                .setId(city.getId())
                .setName(city.getName())
                .setCountryId(city.getCountry().getId())
                .build();
        streamObserver.onNext(protoCity);
        streamObserver.onCompleted();
    }
}
