package com.ngleanhvu.location_service.grpc.service;

import com.ngleanhvu.common.proto.Country;
import com.ngleanhvu.common.proto.CountryRequest;
import com.ngleanhvu.common.proto.CountryServiceGrpc;
import com.ngleanhvu.location_service.country.CountryRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class CountryGrpcService extends CountryServiceGrpc.CountryServiceImplBase {

    private final CountryRepository countryRepository;

    @Override
    public void getCountryById (CountryRequest request, StreamObserver<Country> responseObserver) {
        int countryId = request.getId();

        var country = countryRepository.findById(countryId);

        Country countryProto = null;

        if (country.isPresent()) {
            countryProto = Country.newBuilder()
                    .setId(countryId)
                    .setName(country.get().getName())
                    .setCode(country.get().getCode())
                    .build();
        }

        responseObserver.onNext(countryProto);
        responseObserver.onCompleted();
    }

}
