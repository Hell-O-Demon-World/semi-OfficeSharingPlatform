package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
@RequiredArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id; //대여공간식별번호

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long companyId; //업체식별번호


    private String name;
    private String description;
    private Map<String, Boolean> placeOpen;
    private Date placeStartTime;
    private Date placeEndTime;
    private Map<String, Boolean> placeAddInfo;
    private int postalCode; //우편번호
}
