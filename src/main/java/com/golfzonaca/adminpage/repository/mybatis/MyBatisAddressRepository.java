package com.golfzonaca.adminpage.repository.mybatis;

import com.golfzonaca.adminpage.domain.Address;
import com.golfzonaca.adminpage.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MyBatisAddressRepository implements AddressRepository {

    private final AddressMapper addressMapper;

    @Override
    public Address save(Address address) {
        addressMapper.save(address);
        return address;
    }

    @Override
    public Optional<Address> findByAddressId(Long id) {
        return addressMapper.findByAddressId(id);
    }
}
