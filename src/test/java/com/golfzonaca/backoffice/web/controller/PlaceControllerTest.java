package com.golfzonaca.backoffice.web.controller;

import com.golfzonaca.backoffice.domain.Place;
import com.golfzonaca.backoffice.repository.PlaceRepository;
import com.golfzonaca.backoffice.service.PlaceService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

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
        Place placeA = new Place(2L, 2L, "테스트1", "시험1입니다.", "[Mon, Tue, Wed]", "11:00:00", "22:00:00", "[Wifi, Coffee]", 1L);

        placeService.save(placeA);

        Place placeB = new Place(2L, 2L, "테스트1", "시험1입니다.", "[Mon, Tue, Wed]", "11:00:00", "22:00:00", "[Wifi, Coffee]", 1L);
        placeService.save(placeB);

        //When
        Optional<List<Place>> result = placeService.findAll(2L);

        //Then
        System.out.println("result : " + result.get());
        assertThat(result.get()).isIn(placeA, placeB);
    }

    @Test
    void FindById() {
        //Given
        Place placeA = new Place(2L, 2L, "테스트1", "시험1입니다.", "[Mon, Tue, Wed]", "11:00:00", "22:00:00", "[Wifi, Coffee]", 1L);
        placeService.save(placeA);

        //When
        Optional<Place> findPlace = placeService.findById(2L);

        //Then
        assertThat(findPlace.get()).isSameAs(placeA);
    }


    @Test
    void 공간생성() {
        //Given
        Place place = new Place(2L, 2L, "테스트1", "시험1입니다.", "[Mon, Tue, Wed]", "11:00:00", "22:00:00", "[Wifi, Coffee]", 1L);
        //When
        Place savedPlace = placeService.save(place);
        //Then
        assertThat(savedPlace.getPlaceName()).isEqualTo(place.getPlaceName());
    }


    @Test
    void 공간수정() {
        //Given

        //When

        //Then
    }

    @Test
    void 공간삭제() {
        //Given

        //When

        //Then
    }
}