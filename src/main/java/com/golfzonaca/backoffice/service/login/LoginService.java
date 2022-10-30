package com.golfzonaca.backoffice.service.login;

import com.golfzonaca.backoffice.domain.Company;
import com.golfzonaca.backoffice.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final CompanyService companyService;

    public Company login(String companyLoginId, String password) {
        return null;
    }
}
