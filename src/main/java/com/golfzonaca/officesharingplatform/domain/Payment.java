package com.golfzonaca.officesharingplatform.domain;

import java.util.concurrent.atomic.AtomicLong;

public class Payment {
    private AtomicLong id;
    private AtomicLong userId;
    private AtomicLong companyId;
    private AtomicLong placeId;
    private int totalPrice;
    private boolean status; // 보증금 or 완납
    private int expectedMileage; // 적립 될 마일리지
}
