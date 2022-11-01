package com.golfzonaca.backoffice.service.company;

import com.golfzonaca.backoffice.domain.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {

    Company save(Company company);

    List<Company> findAll();

    Optional<Company> findByCompanyLoginId(String loginId);
}