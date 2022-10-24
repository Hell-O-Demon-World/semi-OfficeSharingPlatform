package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
@RequiredArgsConstructor
public class Place {

    private AtomicLong id; //대여공간식별번호
    private AtomicLong companyId; //업체식별번호
    private String name;
    private String description;
    private Map<String, Boolean> placeOpen;
    private LocalDateTime placeStartTime;
    private LocalDateTime placeEndTime;
    private Map<String, Boolean> placeAddInfo;
    private int postalCode; //우편번호
}