package com.golfzonaca.backoffice.repository;

import com.golfzonaca.backoffice.domain.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository {

    Company save(Company company);

    List<Company> findAll();

    Optional<Company> findByCompanyLoginId(String companyLoginId);
}