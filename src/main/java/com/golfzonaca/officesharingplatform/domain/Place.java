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

    private long id;
    private long companyId;
    private String placeName;
    private String placeDescription;
    private Map<String, Boolean> placeOpendays;
    private Date placeStart;
    private Date placeEnd;
    private Map<String, Boolean> placeAddinfo;
    private int addressId;
}
