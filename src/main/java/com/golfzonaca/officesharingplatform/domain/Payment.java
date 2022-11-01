package com.golfzonaca.officesharingplatform.domain;

public class Payment {
    private long id;
    private long userId;
    private long companyId;
    private long placeId;
    private int totalPrice;
    private boolean status; // 보증금 or 완납
    private int expectedMileage; // 적립 될 마일리지
}
