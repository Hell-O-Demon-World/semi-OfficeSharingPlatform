package com.golfzonaca.officesharingplatform.service.room;

import com.golfzonaca.officesharingplatform.repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyBatisRoomService implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public List<Long> findRoomIdByPlaceIdAndRoomTypeId(long placeId, long roomTypeId) {
        return roomRepository.findRoomIdByPlaceIdAndRoomTypeId(placeId, roomTypeId);
    }

    @Override
    public int countRoomQuantityByPlaceId(long placeId, long roomTypeId) {
        return roomRepository.countRoomQuantityByPlaceId(placeId, roomTypeId);
    }

    @Override
    public List<Integer> findRoomTypeByPlaceId(long placeId) {
        return roomRepository.findRoomTypeByPlaceId(placeId);
    }
}
