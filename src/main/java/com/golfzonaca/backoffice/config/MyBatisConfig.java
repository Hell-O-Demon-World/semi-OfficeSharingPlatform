package com.golfzonaca.backoffice.config;

import com.golfzonaca.backoffice.repository.CompanyRepository;
import com.golfzonaca.backoffice.repository.LocationRepository;
import com.golfzonaca.backoffice.repository.PlaceRepository;
import com.golfzonaca.backoffice.repository.RoomRepository;
import com.golfzonaca.backoffice.repository.mybatis.*;
import com.golfzonaca.backoffice.service.address.LocationService;
import com.golfzonaca.backoffice.service.address.MyBatisLocationService;
import com.golfzonaca.backoffice.service.company.CompanyService;
import com.golfzonaca.backoffice.service.company.MyBatisCompanyService;
import com.golfzonaca.backoffice.service.place.MyBatisPlaceService;
import com.golfzonaca.backoffice.service.place.PlaceService;
import com.golfzonaca.backoffice.service.room.MyBatisRoomService;
import com.golfzonaca.backoffice.service.room.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {
    private final PlaceMapper placeMapper;
    private final CompanyMapper companyMapper;
    private final LocationMapper locationMapper;
    private final RoomMapper roomMapper;

    @Bean
    public PlaceService placeService() {
        return new MyBatisPlaceService(placeRepository());
    }

    @Bean
    public PlaceRepository placeRepository() {
        return new MyBatisPlaceRepository(placeMapper);
    }

    @Bean
    public CompanyService companyService() {
        return new MyBatisCompanyService(companyRepository());
    }

    @Bean
    public CompanyRepository companyRepository() {
        return new MyBatisCompanyRepository(companyMapper);
    }

    @Bean
    public LocationService addressService() {
        return new MyBatisLocationService(addressRepository());
    }

    @Bean
    public LocationRepository addressRepository() {
        return new MyBatisLocationRepository(locationMapper);
    }

    @Bean
    public RoomService roomService() {
        return new MyBatisRoomService(roomRepository());
    }

    @Bean
    public RoomRepository roomRepository() {
        return new MyBatisRoomRepository(roomMapper);
    }
}