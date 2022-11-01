package com.golfzonaca.backoffice.config;

import com.golfzonaca.backoffice.repository.AddressRepository;
import com.golfzonaca.backoffice.repository.CompanyRepository;
import com.golfzonaca.backoffice.repository.PlaceRepository;
import com.golfzonaca.backoffice.repository.mybatis.*;
import com.golfzonaca.backoffice.service.address.AddressService;
import com.golfzonaca.backoffice.service.address.MyBatisAddressService;
import com.golfzonaca.backoffice.service.company.CompanyService;
import com.golfzonaca.backoffice.service.company.MyBatisCompanyService;
import com.golfzonaca.backoffice.service.login.LoginService;
import com.golfzonaca.backoffice.service.place.MyBatisPlaceService;
import com.golfzonaca.backoffice.service.place.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final PlaceMapper placeMapper;
    private final CompanyMapper companyMapper;
    private final AddressMapper addressMapper;

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
    public AddressService addressService() {
        return new MyBatisAddressService(addressRepository());
    }

    @Bean
    public AddressRepository addressRepository() {
        return new MyBatisAddressRepository(addressMapper);
    }

    @Bean
    public LoginService loginService() {
        return new LoginService(new MyBatisCompanyService(new MyBatisCompanyRepository(companyMapper)));
    }
}