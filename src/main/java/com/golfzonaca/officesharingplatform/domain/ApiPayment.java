package com.golfzonaca.officesharingplatform.domain;

import com.golfzonaca.officesharingplatform.domain.type.PayType;

public class ApiPayment {
    private long id;
    private long transactionId;
    private PayType payType;
    private String ApiPayResultCode; // 불변
}
