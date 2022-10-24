package com.golfzonaca.officesharingplatform.domain;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

public class inquiryBoard {
    private AtomicLong id;
    private AtomicLong userId;
    private String title;
    private String content;
    private boolean status;
    private Date writeDate;
}
