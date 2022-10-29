package com.golfzonaca.backoffice.service;

import com.golfzonaca.backoffice.domain.Company;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    Company save(Company company);

    void delete(Long id);

    Optional<Company> findByCompanyId(Long id);

    Optional<List<Company>> findAllCompanyId(Long id);
}
