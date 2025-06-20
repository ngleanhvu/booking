package com.ngleanhvu.auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ngleanhvu.common.grpc_client", "com.ngleanhvu.common.config",
                               "com.ngleanhvu.common.upload",
                               "com.ngleanhvu.auth_service"})
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
