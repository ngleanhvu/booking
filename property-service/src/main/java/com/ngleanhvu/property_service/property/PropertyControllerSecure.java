package com.ngleanhvu.property_service.property;

import com.ngleanhvu.property_service.property.dto.PropertyDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/properties/secure")
@Slf4j
public class PropertyControllerSecure {

    private final PropertyService propertyService;

    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    public void createProperty(@Valid @ModelAttribute PropertyDto propertyDto) throws IOException {
        this.propertyService.createProperty(propertyDto);
    }
}
