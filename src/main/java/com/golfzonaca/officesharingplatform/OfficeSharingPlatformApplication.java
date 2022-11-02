package com.golfzonaca.officesharingplatform;

import com.golfzonaca.officesharingplatform.config.MyBatisConfig;
import com.golfzonaca.officesharingplatform.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Import({SecurityConfig.class, MyBatisConfig.class})
@SpringBootApplication
public class OfficeSharingPlatformApplication {

	public static void main(String[] args) {
		SpringApplication.run(OfficeSharingPlatformApplication.class, args);

	}
}
