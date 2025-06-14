package com.ngleanhvu.common.grpc_client;

import com.ngleanhvu.common.proto.District;
import com.ngleanhvu.common.proto.DistrictRequest;
import com.ngleanhvu.common.proto.DistrictServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class DistrictGrpcClient {
    @GrpcClient("location-service")
    private DistrictServiceGrpc.DistrictServiceBlockingStub districtStub;

    public District getDistrictById(int id) {
        var request = DistrictRequest.newBuilder()
                .setId(id)
                .build();
        var response = districtStub.getDistrictById(request);
        return response;
    }
}