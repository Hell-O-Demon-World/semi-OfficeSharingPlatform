package com.golfzonaca.backoffice.service.address;

import com.golfzonaca.backoffice.domain.Location;
import com.golfzonaca.backoffice.repository.dto.LocationUpdateDto;

public interface LocationService {

    Location save(Location address);

    void update(long id, LocationUpdateDto updateParam);

    Location findByAddressId(long id);
}
