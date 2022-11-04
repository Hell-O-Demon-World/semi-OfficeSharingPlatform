package com.golfzonaca.officesharingplatform.repository.room;

import com.golfzonaca.officesharingplatform.domain.Room;
import com.golfzonaca.officesharingplatform.repository.mybatis.RoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MyBatisRoomRepository implements RoomRepository{
    private final RoomMapper roomMapper;

    @Override
    public List<Room> findRoomByPlaceIdAndRoomKindId(Long placeId, Long roomKindId) {
        return roomMapper.findRoomByPlaceIdAndRoomKindId(placeId, roomKindId);
    }
}
