package com.golfzonaca.adminpage.service;

import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.CompanySearchCond;

import java.util.List;

public interface CompanyService {

    List<Company> findCompanies(CompanySearchCond companySearch);
}
