package com.golfzonaca.adminpage.repository.mybatis;

import com.golfzonaca.adminpage.domain.Company;
import com.golfzonaca.adminpage.repository.CompanySearchCond;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {

    List<Company> findCompanies(CompanySearchCond companySearch);
}
