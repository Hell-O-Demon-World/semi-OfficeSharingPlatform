package com.golfzonaca.officesharingplatform.service.mileage;

import com.golfzonaca.officesharingplatform.domain.Mileage;
import com.golfzonaca.officesharingplatform.repository.mileage.MileageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MemoryMileageService implements MileageService{
    private final MileageRepository mileageRepository;
    private final int initialPoint = 0;
    public Mileage join() {

        Mileage mileage = new Mileage();
        mileage.setPoint(initialPoint);

        mileageRepository.save(mileage);

        return mileage;
    }
}
