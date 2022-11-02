package com.golfzonaca.officesharingplatform.web.auth;

import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.service.auth.MemoryAuthService;
import com.golfzonaca.officesharingplatform.web.auth.form.SignUpSaveForm;
import com.golfzonaca.officesharingplatform.web.auth.form.prefertype.PreferType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.ConcurrentHashMap;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final MemoryAuthService memoryAuthService;

    @ResponseBody
    @GetMapping("/mypage")
    public String success() {
        return "success";
    }

    @ResponseBody
    @PostMapping("/signup")
    public ConcurrentHashMap<String, Object> signup(@Valid @RequestBody SignUpSaveForm signUpSaveForm, BindingResult bindingResult) {

        ConcurrentHashMap<String, Object> errorMap = new ConcurrentHashMap<>();
        if (bindingResult.hasErrors()) {
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                errorMap.put(objectError.getCode() + "ValidationError", objectError.getDefaultMessage());
            }
            return errorMap;
        }

        User user = signUpSaveForm.toEntity();
        if (!memoryAuthService.join(user)) {
            errorMap.put("EmailError", "중복된 이메일 입니다.");
            return errorMap;
        }

        return errorMap;
    }
}
