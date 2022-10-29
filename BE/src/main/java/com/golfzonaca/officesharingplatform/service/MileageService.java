package com.golfzonaca.officesharingplatform.service;

import com.golfzonaca.officesharingplatform.domain.Mileage;
import com.golfzonaca.officesharingplatform.repository.mileage.MileageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MileageService {
    private final MileageRepository mileageRepository;
    private final int initialPoint = 0;
    public Mileage join() {

        Mileage mileage = new Mileage();
        mileage.setPoint(initialPoint);

        mileageRepository.save(mileage);

        return mileage;
    }
}
