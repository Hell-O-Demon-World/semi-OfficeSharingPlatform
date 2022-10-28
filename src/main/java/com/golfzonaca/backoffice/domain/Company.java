package com.golfzonaca.backoffice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Company {

    private Long id; //업체식별번호
    private String companyName;
    private String companyTel;
    private String companyRegNum;
    private String companyRepName;
    private Long addressId;
}
