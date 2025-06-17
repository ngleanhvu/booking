package com.ngleanhvu.search_service.controller;

import com.ngleanhvu.common.response.ApiResponse;
import com.ngleanhvu.common.response.PagingSearch;
import com.ngleanhvu.search_service.document.PropertyDocument;
import com.ngleanhvu.search_service.dto.PropertySearchDto;
import com.ngleanhvu.search_service.service.PropertySearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/property-documents")
public class PropertyDocumentController {
    private final PropertySearchService propertySearchService;

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<PropertyDocument>>> searchPropertyDocuments(@RequestParam Map<String, String> params) {
        String title = params.get("title") == null ? null : params.get("title").trim();
        String description = params.get("description") == null ? null : params.get("description").trim();
        Integer propertyType = params.get("propertyType") == null ? null : Integer.parseInt(params.get("propertyType").trim());
        Integer roomType = params.get("roomType") == null ? null : Integer.parseInt(params.get("roomType").trim());
        Integer city = params.get("city") == null ? null : Integer.parseInt(params.get("city").trim());
        Integer country = params.get("country") == null ? null : Integer.parseInt(params.get("country").trim());
        Integer district = params.get("district") == null ? null : Integer.parseInt(params.get("district").trim());
        Integer ward = params.get("ward") == null ? null : Integer.parseInt(params.get("ward").trim());
        Integer numBeds = params.get("beds") == null ? null : Integer.parseInt(params.get("beds").trim());
        Integer numBaths = params.get("baths") == null ? null : Integer.parseInt(params.get("baths").trim());
        BigDecimal price = params.get("price") == null ? null : new BigDecimal(params.get("price").trim());

        PropertySearchDto propertySearchDto = PropertySearchDto.builder()
                .title(title)
                .description(description)
                .propertyType(propertyType)
                .roomType(roomType)
                .country(country)
                .city(city)
                .ward(ward)
                .district(district)
                .price(price)
                .numBaths(numBaths)
                .numBeds(numBeds)
                .build();

        var response = this.propertySearchService
                .search(propertySearchDto, new PagingSearch());

        return ApiResponse.success("Get list property search success", response.getContent());
    }
}
