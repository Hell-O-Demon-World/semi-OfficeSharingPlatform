package com.golfzonaca.officesharingplatform.web.auth;

import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetails;
import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetailsRepository;
import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetailsService;
import com.golfzonaca.officesharingplatform.config.auth.repository.User2Repository;
import com.golfzonaca.officesharingplatform.config.auth.token.JwtTokenProvider;
import com.golfzonaca.officesharingplatform.config.auth.token.JwtUtil;
import com.golfzonaca.officesharingplatform.config.auth.token.User2;
import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.repository.user.MemoryUserRepository;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import com.golfzonaca.officesharingplatform.service.AuthService;
import com.golfzonaca.officesharingplatform.web.auth.form.SignInSaveForm;
import com.golfzonaca.officesharingplatform.web.auth.form.SignUpSaveForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final User2Repository user2Repository;
    private final PrincipalDetailsRepository principalDetailsRepository;


    @GetMapping("/signin")
    public String signinForm() {

        return "/auth/signin";
    }

//    @ResponseBody
//    @PostMapping("/signin")
//    public String signIn(@Valid @RequestBody SignInSaveForm signInSaveForm, CsrfToken csrfToken) {
//
//        // 인증 성공 후 인증된 user의 정보를 갖고옴
//        System.out.println("signInSaveForm = " + signInSaveForm);
//        PrincipalDetails principalDetails = principalDetailsRepository.findByEmail(signInSaveForm.getUsername());
//        System.out.println("principalDetails = " + principalDetails);
////        PrincipalDetails user = userRepository.findByEmail(signInSaveForm.getUsername());
//        return jwtTokenProvider.createToken(principalDetails.getUsername(), principalDetails.getAuthorities());
//    }
    @GetMapping("/signup")
    public String signupForm() {
        return "/auth/signup";
    }

    @ResponseBody
    @PostMapping("/signup")
    public ConcurrentHashMap<String, Boolean> signup(@Valid @RequestBody SignUpSaveForm signUpSaveForm, BindingResult bindingResult) {
        ConcurrentHashMap<String, Boolean> errorMap = new ConcurrentHashMap<>();
        // 검증이 완료되면
        errorMap.put("signUpStatus", true);
        User user = signUpSaveForm.toEntity();
        User userEntity = authService.join(user);
        PrincipalDetails principalDetails = new PrincipalDetails(userEntity.getMail(), userEntity.getPw(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        User2 user2 = new User2();
        user2.setAuthority("ROLE_USER");
        user2.setPassword(signUpSaveForm.getPassword());
        user2.setUsername(signUpSaveForm.getEmail());
        userRepository.save(user);
        user2Repository.save(user.getId(), user2);
        principalDetailsRepository.save(user.getId(), principalDetails);
        System.out.println("회원가입 완료! principalDetails = " + principalDetails);
        System.out.println("user2 = " + user2);
        System.out.println("user = " + user);
        return errorMap;
    }
    // 인증요청에 대한 응답 객체
    @AllArgsConstructor
    @Data
    static class LoginSuccessResponse {
        private String token;
    }
}
