package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Place {

    private long id; //대여공간식별번호
    private long companyId; //업체식별번호
    private String name;
    private String description;
    private Map<String, Boolean> placeOpen;
    private Date placeStartTime;
    private Date placeEndTime;
    private Map<String, Boolean> placeAddInfo;
    private int postalCode; //우편번호
}
