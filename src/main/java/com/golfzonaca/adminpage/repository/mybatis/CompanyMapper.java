package com.golfzonaca.adminpage.repository.mybatis;

import com.golfzonaca.adminpage.domain.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {

    List<Company> findAllCompanyId(Long id);
}
