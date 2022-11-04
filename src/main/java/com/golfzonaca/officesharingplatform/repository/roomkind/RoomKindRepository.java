package com.golfzonaca.officesharingplatform.repository.roomkind;

public interface RoomKindRepository {
    Long findIdByRoomType(String roomType);
}
