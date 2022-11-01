package com.golfzonaca.backoffice.service.company;

import com.golfzonaca.backoffice.domain.Company;
import com.golfzonaca.backoffice.repository.CompanyRepository;
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
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findByCompanyLoginId(String companyLoginId) {
        return companyRepository.findByCompanyLoginId(companyLoginId);
    }
}
