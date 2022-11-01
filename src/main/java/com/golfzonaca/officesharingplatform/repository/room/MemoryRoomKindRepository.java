package com.golfzonaca.officesharingplatform.repository.room;

import com.golfzonaca.officesharingplatform.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryRoomKindRepository {
    private static final ConcurrentHashMap<Long, Room> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    

}
