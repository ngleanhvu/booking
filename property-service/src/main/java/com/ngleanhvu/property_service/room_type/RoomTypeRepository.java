package com.ngleanhvu.property_service.room_type;

import com.ngleanhvu.property_service.room_type.entity.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomTypeRepository extends JpaRepository<RoomType, Integer> {
}
