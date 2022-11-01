package com.golfzonaca.backoffice.repository.mybatis;

import com.golfzonaca.backoffice.domain.Address;
import com.golfzonaca.backoffice.repository.dto.AddressUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AddressMapper {

    void save(Address address);

    void update(@Param("id") long id, @Param("updateParam") AddressUpdateDto updateParam);

    Address findByAddressId(long id);

}
