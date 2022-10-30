package com.golfzonaca.adminpage.service;

import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyBatisCompanyService implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public List<Company> findAllCompanyId(Long id) {
        return companyRepository.findAllCompanyId(id);
    }
}