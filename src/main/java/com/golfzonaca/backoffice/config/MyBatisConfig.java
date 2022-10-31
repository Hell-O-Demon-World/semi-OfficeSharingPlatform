package com.golfzonaca.backoffice.config;

import com.golfzonaca.backoffice.repository.CompanyRepository;
import com.golfzonaca.backoffice.repository.PlaceRepository;
import com.golfzonaca.backoffice.repository.mybatis.CompanyMapper;
import com.golfzonaca.backoffice.repository.mybatis.MyBatisCompanyRepository;
import com.golfzonaca.backoffice.repository.mybatis.MyBatisPlaceRepository;
import com.golfzonaca.backoffice.repository.mybatis.PlaceMapper;
import com.golfzonaca.backoffice.service.company.CompanyService;
import com.golfzonaca.backoffice.service.company.MyBatisCompanyService;
import com.golfzonaca.backoffice.service.place.MyBatisPlaceService;
import com.golfzonaca.backoffice.service.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {
    private final PlaceMapper placeMapper;
    private final CompanyMapper companyMapper;
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

}