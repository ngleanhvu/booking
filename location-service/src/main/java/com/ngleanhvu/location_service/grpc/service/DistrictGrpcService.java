package com.ngleanhvu.location_service.grpc.service;

import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.common.proto.District;
import com.ngleanhvu.common.proto.DistrictRequest;
import com.ngleanhvu.common.proto.DistrictServiceGrpc;
import com.ngleanhvu.location_service.district.DistrictRepository;
import com.ngleanhvu.location_service.district.DistrictService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class DistrictGrpcService extends DistrictServiceGrpc.DistrictServiceImplBase {

    private final DistrictRepository districtRepository;

    @Override
    public void getDistrictById (DistrictRequest request,
                                 StreamObserver<District> streamObserver) {
        int districtId = request.getId();

        var district = districtRepository.findById(districtId)
                .orElseThrow(() -> new ResourceNotFoundException("District", "id", String.valueOf(districtId)));

        var districtProto = District.newBuilder()
                .setId(districtId)
                .setName(district.getName())
                .setCityId(district.getCity().getId())
                .build();
        streamObserver.onNext(districtProto);
        streamObserver.onCompleted();
    }
}
