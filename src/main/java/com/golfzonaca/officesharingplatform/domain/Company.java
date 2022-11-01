package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Company {

    private long id; //업체식별번호

    private String companyName;

    private String companyTel;

    private String registrationNumber;

    private String chairmanName;

    private Address addressId;
}
