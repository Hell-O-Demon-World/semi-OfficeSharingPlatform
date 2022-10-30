package com.golfzonaca.adminpage.repository.mybatis;

import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.CompanyRepository;
import com.golfzonaca.adminpage.repository.CompanySearchCond;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Company> findCompanies(CompanySearchCond companySearch) {
        return companyMapper.findCompanies(companySearch);
    }
}
