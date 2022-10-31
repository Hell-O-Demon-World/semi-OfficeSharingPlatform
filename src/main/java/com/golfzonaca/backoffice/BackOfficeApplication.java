package com.golfzonaca.backoffice;

import com.golfzonaca.backoffice.auth.SecurityConfig;
import com.golfzonaca.backoffice.config.MyBatisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import({SecurityConfig.class, MyBatisConfig.class})
@SpringBootApplication(scanBasePackages = "com.golfzonaca.backoffice.web")
public class BackOfficeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackOfficeApplication.class, args);
    }

}
