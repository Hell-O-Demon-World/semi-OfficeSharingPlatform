package com.golfzonaca.officesharingplatform.service.address;

import com.golfzonaca.officesharingplatform.domain.Address;
import com.golfzonaca.officesharingplatform.repository.address.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyBatisAddressService implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> findByAddressIds(List addressIds) {
        return addressRepository.findByAddressIds(addressIds);
    }
}
