package com.golfzonaca.backoffice.repository.mybatis;

import com.golfzonaca.backoffice.domain.Address;
import com.golfzonaca.backoffice.repository.AddressRepository;
import com.golfzonaca.backoffice.repository.dto.AddressUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public void update(long id, AddressUpdateDto updateParam) {
        addressMapper.update(id, updateParam);
    }

    @Override
    public Address findByAddressId(long id) {
        return addressMapper.findByAddressId(id);
    }
}
