package com.golfzonaca.officesharingplatform.repository.place;

import com.golfzonaca.officesharingplatform.domain.Place;
import com.golfzonaca.officesharingplatform.repository.PlaceRepository;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemoryPlaceRepository implements PlaceRepository {
    private static final ConcurrentHashMap<Long, Place> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public Place save(Place place) {

        place.setId(sequence++);
        store.put(place.getId(), place);

        return place;
    }

    @Override
    public Place findById(long placeId) {
        return store.get(placeId);
    }
}
