package com.golfzonaca.backoffice;

import com.golfzonaca.backoffice.auth.SecurityConfig;
import com.golfzonaca.backoffice.config.MyBatisConfig;
import com.golfzonaca.backoffice.repository.mybatis.CompanyMapper;
import com.golfzonaca.backoffice.repository.mybatis.PlaceMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
@Import({SecurityConfig.class, MyBatisConfig.class})
@Configuration
@SpringBootApplication(scanBasePackages = "com.golfzonaca.backoffice.web")
public class BackOfficeApplication{
    public static void main(String[] args) {
        SpringApplication.run(BackOfficeApplication.class, args);
    }
}
