package com.golfzonaca.officesharingplatform.web.auth;

import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetailsRepository;
import com.golfzonaca.officesharingplatform.repository.mileage.MemoryMileageRepository;
import com.golfzonaca.officesharingplatform.repository.user.MemoryUserRepository;
import com.golfzonaca.officesharingplatform.service.auth.MemoryAuthService;
import com.golfzonaca.officesharingplatform.service.mileage.MemoryMileageService;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;

@Controller
class AuthControllerTest {
    MemoryAuthService memoryAuthService = new MemoryAuthService(new MemoryUserRepository()
            , new MemoryMileageService(new MemoryMileageRepository()), new PrincipalDetailsRepository());

    @Test
    public void signup() {

    }
}