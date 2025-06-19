package com.ngleanhvu.property_service.room_type;

import com.ngleanhvu.property_service.room_type.entity.RoomType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/room-types/public")
@RequiredArgsConstructor
public class RoomTypeControllerPublic {

    private final RoomTypeService roomTypeService;

    @GetMapping
    public ResponseEntity<List<RoomType>> getRoomTypes() {
        return ResponseEntity.ok(roomTypeService.getRoomTypes());
    }
}
