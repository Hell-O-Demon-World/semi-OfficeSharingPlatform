package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
@RequiredArgsConstructor
public class Company {

    private AtomicLong id; //업체식별번호
    private String companyName;
    private String companyTel;
    private String registrationNumber;
    private String companyAddress;
    private String chairmanName;
    private int postalCode;
}
