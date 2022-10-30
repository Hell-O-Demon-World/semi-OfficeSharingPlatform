package com.golfzonaca.adminpage.config;

import com.golfzonaca.adminpage.repository.CompanyRepository;
import com.golfzonaca.adminpage.repository.mybatis.CompanyMapper;
import com.golfzonaca.adminpage.repository.mybatis.MyBatisCompanyRepository;
import com.golfzonaca.adminpage.service.CompanyService;
import com.golfzonaca.adminpage.service.MyBatisCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final CompanyMapper companyMapper;

    @Bean
    public CompanyService companyService() {
        return new MyBatisCompanyService(companyRepository());
    }

    @Bean
    public CompanyRepository companyRepository() {
        return new MyBatisCompanyRepository(companyMapper);
    }
}