package com.golfzonaca.officesharingplatform.repository.room;

import com.golfzonaca.officesharingplatform.domain.Room;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryRoomRepository implements RoomRepository{
    private static final ConcurrentHashMap<Long, Room> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;
    @Override
    public Map<Long, Room> findRoomsByPlaceId(long placeId) {

        Iterator<Long> keys = store.keySet().iterator();
        while( keys.hasNext() ){
            Long key = keys.next();
            Room storedRoom = store.get(key);
            if (storedRoom.getPlaceId() == (placeId)){
                store.put(key, storedRoom);
            }
        }
        return store;
    }
}
