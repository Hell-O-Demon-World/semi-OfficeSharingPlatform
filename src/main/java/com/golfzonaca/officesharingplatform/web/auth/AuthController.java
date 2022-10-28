package com.golfzonaca.officesharingplatform.web.auth;

import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.service.AuthService;
import com.golfzonaca.officesharingplatform.web.auth.form.SignUpSaveForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @ResponseBody
    @GetMapping("/mypage")
    public String success() {
            return "success";
        }
    @ResponseBody
    @PostMapping("/signup")
    public ConcurrentHashMap<String, Object> signup(@Valid @RequestBody SignUpSaveForm signUpSaveForm, BindingResult bindingResult) {
        ConcurrentHashMap<String, Object> errorMap = new ConcurrentHashMap<>();

        User user = signUpSaveForm.toEntity();
        User userEntity = authService.join(user);
        //        이메일 중복 검증
        if (userEntity == null) {
            errorMap.put("emailError", "중복된 이메일 입니다.");
            log.error("errorMap={}",errorMap);
            return errorMap;
        }
        return errorMap;
    }
}
