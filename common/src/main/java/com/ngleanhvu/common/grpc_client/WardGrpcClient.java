package com.ngleanhvu.common.grpc_client;

import com.ngleanhvu.common.proto.Ward;
import com.ngleanhvu.common.proto.WardRequest;
import com.ngleanhvu.common.proto.WardServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class WardGrpcClient {
    @GrpcClient("location-service")
    private WardServiceGrpc.WardServiceBlockingStub wardStub;

    public Ward getWardById(int id) {
        var request = WardRequest.newBuilder()
                .setId(id)
                .build();
        var response = wardStub.getWardById(request);
        return response;
    }
}

