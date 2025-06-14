package com.ngleanhvu.property_service;

import com.ngleanhvu.common.grpc_client.CityGrpcClient;
import com.ngleanhvu.common.proto.City;
import com.ngleanhvu.common.proto.CityList;
import com.ngleanhvu.common.upload.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class Test {

    private final CityGrpcClient cityGrpcClient;
    private final S3Service s3Service;

    @GetMapping
    public List<String> test() {
        CityList cityList = cityGrpcClient.getCity(1);
        return cityList.getCitiesList().stream().map(City::getName).toList();
    }

    @GetMapping("/cities/{id}")
    public ResponseEntity<String> getCityById(@PathVariable("id") Integer id) {
        City city = cityGrpcClient.getCityById(id);
        return ResponseEntity.ok(city.getName());
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = s3Service.uploadFile(file);
            return ResponseEntity.ok(fileUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }

}
