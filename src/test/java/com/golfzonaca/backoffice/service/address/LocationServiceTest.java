package com.golfzonaca.backoffice.service.address;

import com.golfzonaca.backoffice.domain.Address;
import com.golfzonaca.backoffice.domain.Location;
import com.golfzonaca.backoffice.repository.dto.LocationUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class LocationServiceTest {

    @Autowired
    LocationService locationService;

    @Test
    void 주소저장() {
        //Given
        Location location = new Location("경기도 성남시 분당구", "11111");
        //When
        Location savedLocation = locationService.save(location);
        //Then
        Location findLocation = locationService.findByAddressId(savedLocation.getId());
        assertThat(savedLocation).isEqualTo(findLocation);
    }

    @Test
    void 주소수정() {
        //Given
        Address address = new Address("경기도 성남시 분당구", "11111");
        Address savedAddress = locationService.save(address);
        long AddressId = savedAddress.getId();
        LocationUpdateDto updateParam = new LocationUpdateDto("서울시 강남구", "00000");
        //When
        locationService.update(AddressId, updateParam);
        //Then
        Address findAddress = locationService.findByAddressId(AddressId);
        assertThat(findAddress.getLocation()).isEqualTo(updateParam.getLocation());
        assertThat(findAddress.getPostalCode()).isEqualTo(updateParam.getPostalCode());
    }

    @Test
    void 주소조회() {
        //Given
        Address address = new Address("경기도 성남시 분당구", "11111");
        Address savedAddress = locationService.save(address);
        long AddressId = savedAddress.getId();
        //When
        Address findAddress = locationService.findByAddressId(AddressId);
        //Then
        assertThat(findAddress).isEqualTo(savedAddress);
    }
}