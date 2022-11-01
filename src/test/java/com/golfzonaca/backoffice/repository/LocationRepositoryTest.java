package com.golfzonaca.backoffice.repository;

import com.golfzonaca.backoffice.domain.Address;
import com.golfzonaca.backoffice.repository.dto.LocationUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@SpringBootTest
class LocationRepositoryTest {

    @Autowired
    LocationRepository locationRepository;

    @Test
    void 주소저장() {
        //Given
        Address address = new Address("경기도 성남시 분당구", "11111");
        //When
        Address savedAddress = locationRepository.save(address);
        //Then
        Address findAddress = locationRepository.findByAddressId(savedAddress.getId());
        assertThat(savedAddress).isEqualTo(findAddress);
    }

    @Test
    void 주소수정() {
        //Given
        Address address = new Address("경기도 성남시 분당구", "11111");
        Address savedAddress = locationRepository.save(address);
        long AddressId = savedAddress.getId();
        LocationUpdateDto updateParam = new LocationUpdateDto("서울시 강남구", "00000");
        //When
        locationRepository.update(AddressId, updateParam);
        //Then
        Address findAddress = locationRepository.findByAddressId(AddressId);
        assertThat(findAddress.getLocation()).isEqualTo(updateParam.getLocation());
        assertThat(findAddress.getPostalCode()).isEqualTo(updateParam.getPostalCode());
    }

    @Test
    void 주소조회() {
        //Given
        Address address = new Address("경기도 성남시 분당구", "11111");
        Address savedAddress = locationRepository.save(address);
        long AddressId = savedAddress.getId();
        //When
        Address findAddress = locationRepository.findByAddressId(AddressId);
        //Then
        assertThat(findAddress).isEqualTo(savedAddress);
    }
}