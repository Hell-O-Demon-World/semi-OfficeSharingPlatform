package com.golfzonaca.officesharingplatform.domain;

import java.util.concurrent.atomic.AtomicLong;

public class BlackList {
    private AtomicLong id;
    private AtomicLong userId;
    private AtomicLong providerId;
    private AtomicLong placeId;
    private String reason;
}
