package com.ngleanhvu.auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.ngleanhvu.common.grpc_client",
                               "com.ngleanhvu.common.constant",
                               "com.ngleanhvu.common.exception",
                               "com.ngleanhvu.common.upload",
                               "com.ngleanhvu.common.async",
                               "com.ngleanhvu.auth_service"})
@EnableDiscoveryClient
public class AuthServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthServiceApplication.class, args);
    }
}
