package com.golfzonaca.officesharingplatform.repository.room;

import com.golfzonaca.officesharingplatform.domain.Room;

import java.util.Map;

public class MyBatisRoomRepository implements RoomRepository{
    @Override
    public Map<Long, Room> findRoomsByPlaceId(long placeId) {
        return null;
    }
}
