package com.golfzonaca.backoffice.config;

import com.golfzonaca.backoffice.repository.MyBatisPlaceRepository;
import com.golfzonaca.backoffice.repository.PlaceRepository;
import com.golfzonaca.backoffice.repository.mybatis.PlaceMapper;
import com.golfzonaca.backoffice.service.MyBatisPlaceService;
import com.golfzonaca.backoffice.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final PlaceMapper placeMapper;

    @Bean
    public PlaceService placeService() {
        return new MyBatisPlaceService(placeRepository());
    }

    @Bean
    public PlaceRepository placeRepository() {
        return new MyBatisPlaceRepository(placeMapper);
    }
}