package com.golfzonaca.backoffice.web.controller;

import com.golfzonaca.backoffice.auth.CompanyPrincipalDetails;
import com.golfzonaca.backoffice.auth.TokenForm;
import com.golfzonaca.backoffice.auth.token.JwtRepostiory;
import com.golfzonaca.backoffice.domain.Company;
import com.golfzonaca.backoffice.web.form.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.net.http.HttpHeaders;
import java.util.Map;

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
