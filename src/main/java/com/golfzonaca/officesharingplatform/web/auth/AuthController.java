package com.golfzonaca.officesharingplatform.web.auth;

import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetails;
import com.golfzonaca.officesharingplatform.config.auth.PrincipalDetailsService;
import com.golfzonaca.officesharingplatform.config.auth.token.JwtUtil;
import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.service.AuthService;
import com.golfzonaca.officesharingplatform.web.auth.form.SignInSaveForm;
import com.golfzonaca.officesharingplatform.web.auth.form.SignUpSaveForm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    private final PrincipalDetailsService principalDetailsService;

    @GetMapping("/signin")
    public String signinForm() {

        return "/auth/signin";
    }

    @ResponseBody
    @PostMapping("/signin")
    public ResponseEntity<LoginSuccessResponse> signIn(@RequestBody SignInSaveForm signInSaveForm, CsrfToken csrfToken) {

        // 인증 성공 후 인증된 user의 정보를 갖고옴
        UserDetails userDetails = principalDetailsService.loadUserByUsername(signInSaveForm.getUsername());
        // subject, claim 모두 UserDetails를 사용하므로 객체를 그대로 전달
        String token = jwtUtil.generateToken(userDetails);
        // 생성된 토큰을 응답 (Test)
        return ResponseEntity.ok(new LoginSuccessResponse(token));
    }
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
        return errorMap;
    }
    // 인증요청에 대한 응답 객체
    @AllArgsConstructor
    @Data
    static class LoginSuccessResponse {
        private String token;
    }
}
