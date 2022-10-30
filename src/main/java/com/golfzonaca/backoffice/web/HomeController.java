/*
package com.golfzonaca.backoffice.web;

import com.golfzonaca.backoffice.domain.Company;
import com.golfzonaca.backoffice.repository.CompanyRepository;
import com.golfzonaca.backoffice.service.company.CompanyService;
import com.golfzonaca.backoffice.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CompanyRepository companyRepository;
    private final CompanyService companyService;
    private final SessionManager sessionManager;

    @GetMapping("/")
    public String backOfficeLoginArgumentResolver(@Login Company loginCompany, Model model) {

        //세션에 회원 데이터가 없으면 home
        if (loginCompany == null) {
            return "login/loginForm";
        }

        //세션이 유지되면 로그인으로 이동
        model.addAttribute("company", loginCompany);
        return "place/places";
    }
}
*/
