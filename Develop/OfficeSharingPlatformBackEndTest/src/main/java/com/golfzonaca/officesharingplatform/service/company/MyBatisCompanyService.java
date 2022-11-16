package com.golfzonaca.officesharingplatform.service.company;


import com.golfzonaca.officesharingplatform.repository.company.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyBatisCompanyService implements CompanyService {
    private final CompanyRepository companyRepository;

    @Override
    public String findOpenDaysById(long placeId) {
        return companyRepository.findOpenDaysById(placeId);
    }
}
