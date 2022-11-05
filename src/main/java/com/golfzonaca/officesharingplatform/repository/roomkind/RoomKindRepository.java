package com.golfzonaca.officesharingplatform.repository.roomkind;

import com.golfzonaca.officesharingplatform.domain.RoomKind;

public interface RoomKindRepository {
    Long findIdByRoomType(String roomType);

    RoomKind findById(long roomId);
}
