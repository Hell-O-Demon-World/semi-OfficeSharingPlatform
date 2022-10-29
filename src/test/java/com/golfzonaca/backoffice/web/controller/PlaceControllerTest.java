package com.golfzonaca.backoffice.web.controller;

import com.golfzonaca.backoffice.domain.Place;
import com.golfzonaca.backoffice.repository.PlaceRepository;
import com.golfzonaca.backoffice.repository.dto.PlaceUpdateDto;
import com.golfzonaca.backoffice.service.PlaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PlaceControllerTest {

    @Autowired
    PlaceService placeService;
    @Autowired
    PlaceRepository placeRepository;

    @Test
    void FindByCompanyId() {
        //Given
        Long companyId = 1L;

        Place placeA = new Place(companyId, "테스트1", "시험1입니다.", "[Mon, Tue, Wed]", "11:00:00", "22:00:00", "[Wifi, Coffee]", 1L);
        Place savedPlaceA = placeService.save(placeA);

        Place placeB = new Place(companyId, "테스트2", "시험2입니다.", "[Mon, Tue, Wed]", "11:00:00", "22:00:00", "[Wifi, Coffee]", 1L);
        Place savedPlaceB = placeService.save(placeB);

        //When
        List<Place> result = placeService.findAll(companyId);

        //Then
        assertThat(result).contains(savedPlaceA, savedPlaceB);
    }

    @Test
    void FindById() {
        //Given
        Place place = new Place(2L, "테스트1", "시험1입니다.", "[Mon, Tue, Wed]", "11:00:00", "22:00:00", "[Wifi, Coffee]", 1L);
        Place savedPlace = placeService.save(place);
        Long id = savedPlace.getId();

        //When
        Optional<Place> findPlace = placeService.findById(id);

        //Then
        assertThat(findPlace.get()).isEqualTo(savedPlace);
    }


    @Test
    void 공간생성() {
        //Given
        Place place = new Place(2L, "테스트1", "시험1입니다.", "[Mon, Tue, Wed]", "11:00:00", "22:00:00", "[Wifi, Coffee]", 1L);
        //When
        Place savedPlace = placeService.save(place);
        //Then
        assertThat(savedPlace).isEqualTo(place);
    }


    @Test
    void 공간수정() {
        //Given
        Place place = new Place(2L, "테스트1", "시험1입니다.", "[Mon, Tue, Wed]", "11:00:00", "22:00:00", "[Wifi, Coffee]", 1L);
        Place savedPlace = placeService.save(place);
        Long placeId = savedPlace.getId();

        //When
        PlaceUpdateDto updateParam = new PlaceUpdateDto("테스트2", "시험2입니다.", "[Sat, Sun]", "10:00:00", "12:00:00", "[Wifi, Coffee, Parking]", 2L);
        placeService.update(placeId, updateParam);

        //Then
        Place findPlace = placeService.findById(placeId).get();
        assertThat(findPlace.getPlaceName()).isEqualTo(updateParam.getPlaceName());
        assertThat(findPlace.getPlaceDescription()).isEqualTo(updateParam.getPlaceDescription());
        assertThat(findPlace.getPlaceOpenDays()).isEqualTo(updateParam.getPlaceOpenDays());
        assertThat(findPlace.getPlaceStart()).isEqualTo(updateParam.getPlaceStart());
        assertThat(findPlace.getPlaceEnd()).isEqualTo(updateParam.getPlaceEnd());
        assertThat(findPlace.getPlaceAddInfo()).isEqualTo(updateParam.getPlaceAddInfo());
        assertThat(findPlace.getAddressId()).isEqualTo(updateParam.getAddressId());
    }

    @Test
    void 공간삭제() {
        //Given
        Place place = new Place(2L, "테스트1", "시험1입니다.", "[Mon, Tue, Wed]", "11:00:00", "22:00:00", "[Wifi, Coffee]", 1L);
        Place savedPlace = placeService.save(place);
        Long placeId = savedPlace.getId();

        //When
        placeService.delete(placeId);

        //Then
        Optional<Place> result = placeService.findById(placeId);
        assertThat(result).isEmpty();

    }
}