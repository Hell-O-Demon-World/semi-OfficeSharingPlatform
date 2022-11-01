package com.golfzonaca.officesharingplatform.repository.mileage;

import com.golfzonaca.officesharingplatform.domain.Mileage;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
@Repository
public class MemoryMileageRepository implements MileageRepository{
    private static final ConcurrentHashMap<Long, Mileage> store = new ConcurrentHashMap<>();

    private static long sequence = 0L;

    @Override
    public Mileage save(Mileage mileage) {
        mileage.setId(sequence++);
        store.put(mileage.getId(), mileage);
        return mileage;
    }

    @Override
    public Mileage findById(long id) {
        return null;
    }

    @Override
    public Mileage update(long id, Mileage mileage) {
        return null;
    }

    @Override
    public List<Mileage> findAll() {
        return null;
    }

    @Override
    public void clearStore() {

    }
}
