package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Company {

    private long id; //업체식별번호
    private String companyLoginid;
    private String companyPw;
    private String companyTel;
    private String companyRegnum;
    private String companyRepname;
    private Address addressId;
}
