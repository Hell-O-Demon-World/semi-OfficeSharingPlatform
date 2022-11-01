package com.golfzonaca.adminpage.repository.mybatis;

import com.golfzonaca.adminpage.domain.Address;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface AddressMapper {

    void save(Address address);

    Optional<Address> findByAddressId(Long id);
}
