package com.ngleanhvu.user_service.grpc;

import com.ngleanhvu.common.proto.UserRequestWithEmail;
import com.ngleanhvu.common.proto.UserRequestWithId;
import com.ngleanhvu.common.proto.UserRequestWithPhone;
import com.ngleanhvu.common.proto.UserServiceGrpc;
import com.ngleanhvu.user_service.entity.User;
import com.ngleanhvu.user_service.repo.UserRepo;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.Optional;

@GrpcService
@RequiredArgsConstructor
public class UserGrpcService extends UserServiceGrpc.UserServiceImplBase {

    private final UserRepo userRepo;

    @Override
    public void getUserById (UserRequestWithId request, StreamObserver<com.ngleanhvu.common.proto.User> streamObserver) {
        String id = request.getId();

        Optional<User> optionalUser = userRepo.findById(id);
        com.ngleanhvu.common.proto.User userProto = null;

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userProto = com.ngleanhvu.common.proto.User.newBuilder()
                    .setId(id)
                    .setUsername(user.getUsername())
                    .setActive(user.getActive())
                    .setEmail(user.getEmail())
                    .setPhone(user.getPhone())
                    .setAvatar(user.getAvatar())
                    .build();
        }

        streamObserver.onNext(userProto);
        streamObserver.onCompleted();
    }

    @Override
    public void getUserByEmail (UserRequestWithEmail request, StreamObserver<com.ngleanhvu.common.proto.User> streamObserver) {
        String email = request.getEmail();
        Optional<User> optionalUser = userRepo.findByEmail(email);
        com.ngleanhvu.common.proto.User userProto = null;

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userProto = com.ngleanhvu.common.proto.User.newBuilder()
                    .setId(user.getId())
                    .setUsername(user.getUsername())
                    .setActive(user.getActive())
                    .setEmail(user.getEmail())
                    .setPhone(user.getPhone())
                    .setAvatar(user.getAvatar())
                    .build();
        }

        streamObserver.onNext(userProto);
        streamObserver.onCompleted();
    }

    @Override
    public void getUserByPhone (UserRequestWithPhone request, StreamObserver<com.ngleanhvu.common.proto.User> streamObserver) {
        String phone = request.getPhone();
        Optional<User> optionalUser = userRepo.findByPhone(phone);
        com.ngleanhvu.common.proto.User userProto = null;

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            userProto = com.ngleanhvu.common.proto.User.newBuilder()
                    .setId(user.getId())
                    .setUsername(user.getUsername())
                    .setActive(user.getActive())
                    .setEmail(user.getEmail())
                    .setPhone(user.getPhone())
                    .setAvatar(user.getAvatar())
                    .build();
        }

        streamObserver.onNext(userProto);
        streamObserver.onCompleted();
    }
}
