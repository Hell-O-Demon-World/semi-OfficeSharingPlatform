package com.golfzonaca.backoffice.service.address;

import com.golfzonaca.backoffice.domain.Location;
import com.golfzonaca.backoffice.repository.LocationRepository;
import com.golfzonaca.backoffice.repository.dto.LocationUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyBatisLocationService implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public Location save(Location address) {
        return locationRepository.save(address);
    }

    @Override
    public void update(long id, LocationUpdateDto updateParam) {
        locationRepository.update(id, updateParam);
    }

    @Override
    public Location findByAddressId(long id) {
        return locationRepository.findByAddressId(id);
    }
}
