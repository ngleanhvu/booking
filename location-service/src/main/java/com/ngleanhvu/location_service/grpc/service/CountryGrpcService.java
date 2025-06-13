package com.ngleanhvu.location_service.grpc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ngleanhvu.common.proto.CityList;
import com.ngleanhvu.common.proto.Country;
import com.ngleanhvu.common.proto.CountryRequest;
import com.ngleanhvu.common.proto.CountryServiceGrpc;
import com.ngleanhvu.location_service.country.CountryRepository;
import com.ngleanhvu.location_service.country.CountryService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
@RequiredArgsConstructor
public class CountryGrpcService extends CountryServiceGrpc.CountryServiceImplBase {

    private final CountryService countryService;

    @Override
    public void getCountryById (CountryRequest request, StreamObserver<Country> responseObserver) throws JsonProcessingException {
        int countryId = request.getId();

        var country = countryService.findCountryById(countryId);

        var countryProto = Country.newBuilder()
                .setId(countryId)
                .setName(country.getName())
                .setCode(country.getCode())
                .build();
        responseObserver.onNext(countryProto);
        responseObserver.onCompleted();
    }

}
