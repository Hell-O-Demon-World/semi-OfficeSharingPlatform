package com.golfzonaca.backoffice.service.address;

import com.golfzonaca.backoffice.domain.Address;
import com.golfzonaca.backoffice.repository.dto.AddressUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class AddressServiceTest {

    @Autowired
    AddressService addressService;

    @Test
    void 주소저장() {
        //Given
        Address address = new Address("경기도 성남시 분당구", "11111");
        //When
        Address savedAddress = addressService.save(address);
        //Then
        Address findAddress = addressService.findByAddressId(savedAddress.getId());
        assertThat(savedAddress).isEqualTo(findAddress);
    }

    @Test
    void 주소수정() {
        //Given
        Address address = new Address("경기도 성남시 분당구", "11111");
        Address savedAddress = addressService.save(address);
        long AddressId = savedAddress.getId();
        AddressUpdateDto updateParam = new AddressUpdateDto("서울시 강남구", "00000");
        //When
        addressService.update(AddressId, updateParam);
        //Then
        Address findAddress = addressService.findByAddressId(AddressId);
        assertThat(findAddress.getLocation()).isEqualTo(updateParam.getLocation());
        assertThat(findAddress.getPostalCode()).isEqualTo(updateParam.getPostalCode());
    }

    @Test
    void 주소조회() {
        //Given
        Address address = new Address("경기도 성남시 분당구", "11111");
        Address savedAddress = addressService.save(address);
        long AddressId = savedAddress.getId();
        //When
        Address findAddress = addressService.findByAddressId(AddressId);
        //Then
        assertThat(findAddress).isEqualTo(savedAddress);
    }
}