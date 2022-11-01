package com.golfzonaca.backoffice.service.address;

import com.golfzonaca.backoffice.domain.Address;
import com.golfzonaca.backoffice.repository.dto.AddressUpdateDto;

public interface AddressService {

    Address save(Address address);

    void update(long id, AddressUpdateDto updateParam);

    Address findByAddressId(long id);
}
