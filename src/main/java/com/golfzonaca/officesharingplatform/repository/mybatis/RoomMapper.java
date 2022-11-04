package com.golfzonaca.officesharingplatform.repository.mybatis;

import com.golfzonaca.officesharingplatform.domain.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomMapper {
    List<Room> findRoomByPlaceIdAndRoomKindId(Long placeId, Long roomKindId);
}
