package com.golfzonaca.backoffice.repository.mybatis;

import com.golfzonaca.backoffice.domain.Company;
import com.golfzonaca.backoffice.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MyBatisCompanyRepository implements CompanyRepository {

    private final CompanyMapper companyMapper;

    @Override
    public Company save(Company company) {
        companyMapper.save(company);
        return company;
    }

    @Override
    public void delete(Long id) {
        companyMapper.delete(id);
    }

    @Override
    public Optional<Company> findByCompanyId(Long id) {
        return companyMapper.findByCompanyId(id);
    }

    @Override
    public Optional<List<Company>> findAllCompanyId(Long id) {
        return companyMapper.findByAllCompanyId(id);
    }
}