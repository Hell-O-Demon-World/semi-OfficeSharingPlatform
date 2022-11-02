package com.golfzonaca.officesharingplatform.config;

import com.golfzonaca.officesharingplatform.repository.mileage.MileageRepository;
import com.golfzonaca.officesharingplatform.repository.mileage.MyBatisMileageRepository;
import com.golfzonaca.officesharingplatform.repository.mybatis.mapper.MileageMapper;
import com.golfzonaca.officesharingplatform.repository.mybatis.mapper.UserMapper;
import com.golfzonaca.officesharingplatform.repository.user.MyBatisUserRepository;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final UserMapper userMapper;
    private final MileageMapper mileageMapper;

    @Bean
    public UserRepository userRepository() {
        return new MyBatisUserRepository(userMapper);
    }

    @Bean
    public MileageRepository mileageRepository() {
        return new MyBatisMileageRepository(mileageMapper);
    }
}
