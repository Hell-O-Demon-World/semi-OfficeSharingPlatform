package com.golfzonaca.officesharingplatform.service.mileage;

import com.golfzonaca.officesharingplatform.domain.Mileage;
import com.golfzonaca.officesharingplatform.repository.mileage.MileageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MybatisMileageService implements MileageService{
    private final MileageRepository mileageRepository;
    private final int initialPoint = 0;
    @Override
    public Mileage join() {

        Mileage mileage = new Mileage();
        mileage.setPoint(initialPoint);

        mileageRepository.save(mileage);
        return mileage;
    }
}
