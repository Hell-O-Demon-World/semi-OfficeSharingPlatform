package com.golfzonaca.adminpage;

import com.golfzonaca.adminpage.config.MyBatisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(MyBatisConfig.class)
@SpringBootApplication(scanBasePackages = "com.golfzonaca.adminpage.web")
public class AdminPageApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminPageApplication.class, args);
    }
}
