package com.ngleanhvu.common.grpc_client;

import com.ngleanhvu.common.proto.*;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class UserGrpcClient {

    @GrpcClient("user-service")
    public UserServiceGrpc.UserServiceBlockingStub userStub;

    public User getUserById(String id) {
        UserRequestWithId request = UserRequestWithId.newBuilder()
                .setId(id)
                .build();
        var response = userStub.getUserById(request);
        return response;
    }

    public User getUserByEmail(String email) {
        UserRequestWithEmail request = UserRequestWithEmail.newBuilder()
                .setEmail(email)
                .build();
        var response = userStub.getUserByEmail(request);
        return response;
    }

    public User getUserByPhone(String phone) {
        UserRequestWithPhone request = UserRequestWithPhone.newBuilder()
                .setPhone(phone)
                .build();
        var response = userStub.getUserByPhone(request);
        return response;
    }
}
