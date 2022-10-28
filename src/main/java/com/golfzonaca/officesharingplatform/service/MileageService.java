package com.golfzonaca.officesharingplatform.service;

import com.golfzonaca.officesharingplatform.domain.Mileage;
import com.golfzonaca.officesharingplatform.repository.mileage.MileageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MileageService {
    private final MileageRepository mileageRepository;

    public Mileage join() {

        Mileage mileage = new Mileage();
        mileage.setPoint(0);

        mileageRepository.save(mileage);

        return mileage;
    }
}
