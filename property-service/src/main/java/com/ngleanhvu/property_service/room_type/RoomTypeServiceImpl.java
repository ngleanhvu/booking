package com.ngleanhvu.property_service.room_type;

import com.ngleanhvu.property_service.room_type.entity.RoomType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomTypeServiceImpl implements RoomTypeService {

    private final RoomTypeRepository roomTypeRepository;

    @Override
    public List<RoomType> getRoomTypes() {
        return roomTypeRepository.findAll();
    }
}
