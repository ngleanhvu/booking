package com.ngleanhvu.common.grpc_client;

import com.ngleanhvu.common.proto.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class CityGrpcClient {
    @GrpcClient("location-service")
    private CityServiceGrpc.CityServiceBlockingStub cityStub;

    public CityList getCity(int id) {
        var request = CityRequestForCountryId.newBuilder()
                .setCountryId(id)
                .build();
        var response = cityStub.getAllCitiesByCountryId(request);
        return response;
    }

    public City getCityById(int id) {
        var request = CityRequest.newBuilder()
                .setCityId(id)
                .build();
        var response = cityStub.getCityById(request);
        return response;
    }
}
