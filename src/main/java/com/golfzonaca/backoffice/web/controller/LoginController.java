package com.golfzonaca.backoffice.web.controller;

import com.golfzonaca.backoffice.auth.TokenForm;
import com.golfzonaca.backoffice.domain.Company;
import com.golfzonaca.backoffice.web.form.login.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {


    @GetMapping("/signin")
    public String loginForm(@ModelAttribute LoginForm loginForm, Model model) {
        model.addAttribute(loginForm);
        return "/login/loginForm";
    }

    @ResponseBody
    @PostMapping("/auth/signin")
    public String login(@ModelAttribute TokenForm tokenForm, Model model, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }
        log.info("company={}", tokenForm.getUserId());
        model.addAttribute(tokenForm.getUserId());
        return "/places";
    }
//    @PostMapping
//    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "login/loginForm";
//        }
//
//        Company loginCompany = loginService.login(form.getCompanyLoginId(), form.getCompanyPw());
//
//        if (loginCompany == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 일치하지 않습니다.");
//            return "login/loginForm";
//        }
//        return "redirect:place/places";
//    }
}
