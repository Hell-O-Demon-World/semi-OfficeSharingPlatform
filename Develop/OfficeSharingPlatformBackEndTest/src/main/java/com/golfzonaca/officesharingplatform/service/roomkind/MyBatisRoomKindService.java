package com.golfzonaca.officesharingplatform.service.roomkind;

import com.golfzonaca.officesharingplatform.repository.roomkind.RoomKindRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyBatisRoomKindService implements RoomKindService {

    private final RoomKindRepository roomKindRepository;

    @Override
    public Long findIdByRoomType(String roomType) {
        return roomKindRepository.findIdByRoomType(roomType);
    }
}
