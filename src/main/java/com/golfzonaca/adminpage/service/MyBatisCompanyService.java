package com.golfzonaca.adminpage.service;

import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.CompanyRepository;
import com.golfzonaca.adminpage.repository.CompanySearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyBatisCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> findCompanies(CompanySearchCond companySearch) {
        return companyRepository.findCompanies(companySearch);
    }
}