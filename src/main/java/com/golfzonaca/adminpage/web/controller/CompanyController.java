package com.golfzonaca.adminpage.web.controller;

import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.CompanySearchCond;
import com.golfzonaca.adminpage.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping //업체 등록 조회
    public String companies(@ModelAttribute("companySearch") CompanySearchCond companySearch, Model model) {
        List<Company> companies = companyService.findCompanies(companySearch);
        model.addAttribute("companies", companies);
        return "company/companies";
    }
}