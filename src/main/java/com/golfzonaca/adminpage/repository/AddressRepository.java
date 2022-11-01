package com.golfzonaca.adminpage.repository;

import com.golfzonaca.adminpage.domain.Address;

import java.util.Optional;

public interface AddressRepository {

    Address save(Address address);

    Optional<Address> findByAddressId(Long id);
}
