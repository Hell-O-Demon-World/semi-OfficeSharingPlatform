package com.golfzonaca.adminpage.web.controller;

import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.CompanySearchCond;
import com.golfzonaca.adminpage.service.CompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/add")
    public String addCompanyForm() {
        return "/company/addCompanyForm";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute Company company, RedirectAttributes redirectAttributes) {
        Company savedCompany = companyService.save(company);
        log.info("savedCompany={} ",savedCompany);
        redirectAttributes.addAttribute("companyId", savedCompany.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/companies";
    }

    @GetMapping("{id}/delete")
    public String delete(@PathVariable Long id) {
        companyService.delete(id);
        return "redirect:/companies";
    }
}