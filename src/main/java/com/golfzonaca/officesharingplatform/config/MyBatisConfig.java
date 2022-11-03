package com.golfzonaca.officesharingplatform.config;

import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetailsRepository;
import com.golfzonaca.officesharingplatform.repository.mileage.MileageRepository;
import com.golfzonaca.officesharingplatform.repository.mileage.MyBatisMileageRepository;
import com.golfzonaca.officesharingplatform.repository.mybatis.MileageMapper;
import com.golfzonaca.officesharingplatform.repository.mybatis.SearchMapper;
import com.golfzonaca.officesharingplatform.repository.mybatis.UserMapper;
import com.golfzonaca.officesharingplatform.repository.search.MyBatisSearchRepository;
import com.golfzonaca.officesharingplatform.repository.search.SearchRepository;
import com.golfzonaca.officesharingplatform.repository.user.MyBatisUserRepository;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import com.golfzonaca.officesharingplatform.service.auth.AuthService;
import com.golfzonaca.officesharingplatform.service.auth.MyBatisAuthService;
import com.golfzonaca.officesharingplatform.service.mileage.MileageService;
import com.golfzonaca.officesharingplatform.service.mileage.MybatisMileageService;
import com.golfzonaca.officesharingplatform.service.search.MyBatisSearchService;
import com.golfzonaca.officesharingplatform.service.search.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final UserMapper userMapper;
    private final MileageMapper mileageMapper;
    private final SearchMapper searchMapper;

    @Bean
    public AuthService authService() {
        return new MyBatisAuthService(userRepository(), mileageService(), new PrincipalDetailsRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new MyBatisUserRepository(userMapper);
    }

    @Bean
    public MileageRepository mileageRepository() {
        return new MyBatisMileageRepository(mileageMapper);
    }

    @Bean
    public MileageService mileageService() {
        return new MybatisMileageService(mileageRepository());
    }

    @Bean
    public SearchService searchService() {
        return new MyBatisSearchService(searchRepository());
    }

    @Bean
    public SearchRepository searchRepository() {
        return new MyBatisSearchRepository(searchMapper);
    }

}
