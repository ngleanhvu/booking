package com.ngleanhvu.property_service.grpc.client;

import com.ngleanhvu.common.proto.Country;
import com.ngleanhvu.common.proto.CountryRequest;
import com.ngleanhvu.common.proto.CountryServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class CountryGrpcClient {
    @GrpcClient("location-service")
    private CountryServiceGrpc.CountryServiceBlockingStub countryStub;

    public Country getCountryById(int id) {
        var request = CountryRequest.newBuilder()
                .setId(id)
                .build();
        var response = countryStub.getCountryById(request);
        return response;
    }
}
