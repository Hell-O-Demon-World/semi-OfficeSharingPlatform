package com.golfzonaca.officesharingplatform.repository.room;

import com.golfzonaca.officesharingplatform.domain.Room;

import java.util.List;
import java.util.Map;

public interface RoomRepository {
    Map<Long, Room> findRoomsByPlaceId(long placeId);
}
