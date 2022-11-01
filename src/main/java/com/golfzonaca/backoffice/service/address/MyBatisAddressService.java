package com.golfzonaca.backoffice.service.address;

import com.golfzonaca.backoffice.domain.Address;
import com.golfzonaca.backoffice.repository.AddressRepository;
import com.golfzonaca.backoffice.repository.dto.AddressUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyBatisAddressService implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void update(long id, AddressUpdateDto updateParam) {
        addressRepository.update(id, updateParam);
    }

    @Override
    public Address findByAddressId(long id) {
        return addressRepository.findByAddressId(id);
    }
}
