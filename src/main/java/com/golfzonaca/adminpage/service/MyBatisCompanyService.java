package com.golfzonaca.adminpage.service;

import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.CompanyRepository;
import com.golfzonaca.adminpage.repository.CompanySearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyBatisCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public void delete(Long id) {
        companyRepository.delete(id);
    }

    @Override
    public List<Company> findCompanies(CompanySearchCond companySearch) {
        return companyRepository.findCompanies(companySearch);
    }

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }
}