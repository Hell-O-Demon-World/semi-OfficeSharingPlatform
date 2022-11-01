package com.golfzonaca.officesharingplatform.web.auth;

import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetailsRepository;
import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.repository.mileage.MemoryMileageRepository;
import com.golfzonaca.officesharingplatform.repository.user.MemoryUserRepository;
import com.golfzonaca.officesharingplatform.service.AuthService;
import com.golfzonaca.officesharingplatform.service.MileageService;
import com.golfzonaca.officesharingplatform.web.auth.form.SignUpSaveForm;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Controller;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Controller
class AuthControllerTest {
    AuthService authService = new AuthService(new MemoryUserRepository()
            , new MileageService(new MemoryMileageRepository()), new PrincipalDetailsRepository());

    @Test
    public void signup() {

    }
}