package com.golfzonaca.adminpage.service;

import com.golfzonaca.adminpage.domain.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAllCompanyId(Long id);
}
