package com.golfzonaca.officesharingplatform.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Reservation {
    private long id;
    private long placeId;
    private long userId;
    private long roomId;
    private long roomKindId;
    private LocalDateTime resStartTime;
    private LocalDateTime resEndTime;

}
