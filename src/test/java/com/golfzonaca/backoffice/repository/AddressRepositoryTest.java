package com.golfzonaca.backoffice.repository;

import com.golfzonaca.backoffice.domain.Address;
import com.golfzonaca.backoffice.repository.dto.AddressUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@Transactional
@SpringBootTest
class AddressRepositoryTest {

    @Autowired
    AddressRepository addressRepository;

    @Test
    void 주소저장() {
        //Given
        Address address = new Address("경기도 성남시 분당구", "11111");
        //When
        Address savedAddress = addressRepository.save(address);
        //Then
        Address findAddress = addressRepository.findByAddressId(savedAddress.getId());
        assertThat(savedAddress).isEqualTo(findAddress);
    }

    @Test
    void 주소수정() {
        //Given
        Address address = new Address("경기도 성남시 분당구", "11111");
        Address savedAddress = addressRepository.save(address);
        long AddressId = savedAddress.getId();
        AddressUpdateDto updateParam = new AddressUpdateDto("서울시 강남구", "00000");
        //When
        addressRepository.update(AddressId, updateParam);
        //Then
        Address findAddress = addressRepository.findByAddressId(AddressId);
        assertThat(findAddress.getLocation()).isEqualTo(updateParam.getLocation());
        assertThat(findAddress.getPostalCode()).isEqualTo(updateParam.getPostalCode());
    }

    @Test
    void 주소조회() {
        //Given
        Address address = new Address("경기도 성남시 분당구", "11111");
        Address savedAddress = addressRepository.save(address);
        long AddressId = savedAddress.getId();
        //When
        Address findAddress = addressRepository.findByAddressId(AddressId);
        //Then
        assertThat(findAddress).isEqualTo(savedAddress);
    }
}