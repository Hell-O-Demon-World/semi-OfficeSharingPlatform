package com.golfzonaca.officesharingplatform.service.address;

import com.golfzonaca.officesharingplatform.domain.Address;

import java.util.List;

public interface AddressService {
    List<Address> findByAddressIds(List addressIds);
}
