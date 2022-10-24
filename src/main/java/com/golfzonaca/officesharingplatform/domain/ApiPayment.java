package com.golfzonaca.officesharingplatform.domain;

import java.util.concurrent.atomic.AtomicLong;

public class ApiPayment {
    private AtomicLong id;
    private AtomicLong transactionId;
    private PayType payType;
    private String ApiPayResultCode; // 불변
}
