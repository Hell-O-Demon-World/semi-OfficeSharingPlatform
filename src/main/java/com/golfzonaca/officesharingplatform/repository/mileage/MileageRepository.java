package com.golfzonaca.officesharingplatform.repository.mileage;

import com.golfzonaca.officesharingplatform.domain.Mileage;

import java.util.List;

public interface MileageRepository {
    Mileage save(Mileage mileage);

    Mileage findById(long id);

    Mileage update(long id, Mileage mileage);

    List<Mileage> findAll();

    void clearStore();
}
