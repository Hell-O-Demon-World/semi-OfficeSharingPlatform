package com.golfzonaca.backoffice.repository;

import com.golfzonaca.backoffice.domain.Address;
import com.golfzonaca.backoffice.repository.dto.AddressUpdateDto;

public interface AddressRepository {

    Address save(Address address);

    void update(long id, AddressUpdateDto updateParam);

    Address findByAddressId(long id);

}
