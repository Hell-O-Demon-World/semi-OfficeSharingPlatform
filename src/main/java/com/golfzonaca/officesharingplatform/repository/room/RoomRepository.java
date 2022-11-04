package com.golfzonaca.officesharingplatform.repository.room;

import com.golfzonaca.officesharingplatform.domain.Room;

import java.util.List;

public interface RoomRepository {

    List<Room> findRoomByPlaceIdAndRoomKindId(Long placeId, Long roomKindId);
}
