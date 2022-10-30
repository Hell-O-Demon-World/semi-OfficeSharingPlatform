package com.golfzonaca.adminpage.repository;

import com.golfzonaca.adminpage.domain.Company;

import java.util.List;

public interface CompanyRepository {

    List<Company> findAllCompanyId(Long id);
}
