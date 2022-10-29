package com.golfzonaca.backoffice.repository.mybatis;

import com.golfzonaca.backoffice.domain.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CompanyMapper {

    void save(Company company);

    void delete(Long id);

    Optional<Company> findByCompanyId(Long id);

    Optional<List<Company>> findByAllCompanyId(Long id);
}