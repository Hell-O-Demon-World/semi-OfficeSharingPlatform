package com.golfzonaca.backoffice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

@Getter
@ToString
@RequiredArgsConstructor
public class Place {

    private Long id; //대여공간식별번호
    private Long companyId; //업체식별번호
    private String name;
    private String description;
    private Map<String, Boolean> placeOpen;
    private Date placeStartTime;
    private Date placeEndTime;
    private Map<String, Boolean> placeAddInfo;
    private int addressId; //우편번호
}
