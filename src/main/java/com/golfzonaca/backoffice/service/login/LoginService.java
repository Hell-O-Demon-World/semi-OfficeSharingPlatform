package com.golfzonaca.backoffice.service.login;

import com.golfzonaca.backoffice.domain.Company;
import com.golfzonaca.backoffice.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//@RequiredArgsConstructor
//public class LoginService {
//
//    private final CompanyService companyService;
//
//    public Company login(String companyLoginId, String companyPw) {
//        return companyService.findByCompanyLoginId(companyLoginId)
//                .filter(c -> c.getCompanyPw().equals(companyPw))
//                .orElse(null);
//    }
//}
