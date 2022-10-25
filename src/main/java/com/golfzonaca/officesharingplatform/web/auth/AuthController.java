package com.golfzonaca.officesharingplatform.web.auth;

import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import com.golfzonaca.officesharingplatform.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/signin")
    public String signinForm() {
        return "/auth/signin";
    }

    @GetMapping("/signup")
    public String signupForm() {
        return "/auth/signup";
    }

    @PostMapping("/signup")
    public String signup() {

        return "/auth/signin";
    }
}
