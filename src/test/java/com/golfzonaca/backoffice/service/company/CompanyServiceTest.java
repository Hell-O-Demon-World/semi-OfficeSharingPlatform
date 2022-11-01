package com.golfzonaca.backoffice.service.company;

import com.golfzonaca.backoffice.domain.Company;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @Test
    void save() {
        //Given
        Company company = new Company("testacc1", "test1234", "테스트 업체1", "02-1234-1234", "12-12341234", "어나니머스", 1L);
        //When
        Company savedCompany = companyService.save(company);
        //Then
        assertThat(savedCompany.getCompanyName()).isEqualTo(company.getCompanyName());
    }

    @Test
    void findAll() {
        //Given
        Company companyA = new Company("testacc1", "test1234", "테스트 업체1", "02-1234-1234", "12-12341234", "어나니머스", 1L);
        Company savedCompanyA = companyService.save(companyA);
        Company companyB = new Company("testacc2", "test1234", "테스트 업체2", "02-1234-1234", "12-12341234", "이너", 1L);
        Company savedCompanyB = companyService.save(companyB);
        //When
        List<Company> result = companyService.findAll();
        //Then
        assertThat(result).contains(savedCompanyA, savedCompanyB);
    }

    @Test
    void findByCompanyLoginId() {
        //Given
        Company companyA = new Company("testacc1", "test1234", "테스트 업체1", "02-1234-1234", "12-12341234", "어나니머스", 1L);
        Company savedCompanyA = companyService.save(companyA);
        //When
        Optional<Company> findCompany = companyService.findByCompanyLoginId("testacc1");
        //Then
        assertThat(findCompany.get()).isEqualTo(savedCompanyA);
    }
}