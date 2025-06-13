package com.ngleanhvu.location_service.grpc.service;

import com.ngleanhvu.common.exception.ResourceNotFoundException;
import com.ngleanhvu.common.proto.Ward;
import com.ngleanhvu.common.proto.WardRequest;
import com.ngleanhvu.common.proto.WardServiceGrpc;
import com.ngleanhvu.location_service.ward.WardRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class WardGrpcService extends WardServiceGrpc.WardServiceImplBase {

    private final WardRepository wardRepository;

    @Override
    public void getWardById (WardRequest request, StreamObserver<Ward> responseObserver) {

        int wardId = request.getId();

        var ward = wardRepository.findById(wardId)
                .orElseThrow(() -> new ResourceNotFoundException("Ward","id",String.valueOf(wardId)));

        var wardProto = Ward.newBuilder()
                .setId(wardId)
                .setName(ward.getName())
                .setDistrictId(ward.getDistrict().getId())
                .build();
        responseObserver.onNext(wardProto);
        responseObserver.onCompleted();
    }

}
