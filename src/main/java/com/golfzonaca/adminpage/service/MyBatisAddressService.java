package com.golfzonaca.adminpage.service;

import com.golfzonaca.adminpage.domain.Address;
import com.golfzonaca.adminpage.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyBatisAddressService implements AddressService{

    private final AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Optional<Address> findByAddressId(Long id) {
        return addressRepository.findByAddressId(id);
    }
}
