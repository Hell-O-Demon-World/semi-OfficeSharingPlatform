package com.golfzonaca.backoffice.web.controller;

import com.golfzonaca.backoffice.domain.Company;
import com.golfzonaca.backoffice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping //CompanyId로 findAll해
    public String companies(@ModelAttribute("findAllCompanyId") Long id, Model model) {
        Optional<List<Company>> companies = companyService.findAllCompanyId(id);
        model.addAttribute("companies", companies);
        return "companies";
    }

    @GetMapping("/add")
    public String addCompanyForm(Model model) {
        Company company = new Company();
        model.addAttribute(company);
        return "addCompanyForm";
    }

    @PostMapping("/add")
    public String addCompany(@ModelAttribute Company company, RedirectAttributes redirectAttributes) {
        Company savedCompany = companyService.save(company);
        redirectAttributes.addAttribute("id", savedCompany.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/companies/{id}";
    }

    @GetMapping("/{id}delete")
    public String delete(@PathVariable Long id) {
        companyService.delete(id);
        return "redirect:/companies";
    }
}
