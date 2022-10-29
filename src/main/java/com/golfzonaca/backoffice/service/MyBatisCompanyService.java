package com.golfzonaca.backoffice.service;

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
    public void delete(Long id) {
        companyRepository.delete(id);
    }

    @Override
    public Optional<Company> findByCompanyId(Long id) {
        return companyRepository.findByCompanyId(id);
    }

    @Override
    public Optional<List<Company>> findAllCompanyId(Long id) {
        return companyRepository.findAllCompanyId(id);
    }


}
