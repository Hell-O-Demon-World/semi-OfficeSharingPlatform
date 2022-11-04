package com.golfzonaca.officesharingplatform.domain;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Data
public class Reservation {
    private long id;
    private long placeId;
    private long userId;
    private long roomId;
    private long roomKindId;
    private LocalTime resStartTime;
    private LocalTime resEndTime;

}