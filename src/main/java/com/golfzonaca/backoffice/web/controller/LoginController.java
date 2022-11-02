package com.golfzonaca.backoffice.web.controller;

import com.golfzonaca.backoffice.web.form.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {

    @GetMapping("/signin")
    public String loginForm(@ModelAttribute LoginForm loginForm, Model model) {
        log.info("loginForm={}",loginForm);
        model.addAttribute(loginForm);
        return "/login/loginForm";
    }

//    @PostMapping("/signin")
//    public String login(Model model,@AuthenticationPrincipal CompanyPrincipalDetails companyPrincipalDetails) {
//        System.out.println("LoginController.login");
//        log.info("companyDetail={}",companyPrincipalDetails);
//
////        log.info("userId={}", userId);
////        model.addAttribute(tokenForm.getUserId());
//        return "/place/places";
//    }
}
