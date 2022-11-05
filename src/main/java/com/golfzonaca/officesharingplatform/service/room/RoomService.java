package com.golfzonaca.officesharingplatform.service.room;

import java.util.List;

public interface RoomService {

    List<Long> findRoomIdByPlaceIdAndRoomTypeId(long placeId, long RoomTypeId);

    int countRoomQuantityByPlaceId(long placeId, long roomTypeId);

    List<Integer> findRoomTypeByPlaceId(long placeId);


}
