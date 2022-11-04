package com.golfzonaca.officesharingplatform.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Reservation {
    private long id;
    private long placeId;
    private long userId;
    private long roomId;
    private long roomKindId;
    private LocalDate resStartDate;
    private LocalTime resStartTime;
    private LocalDate resEndDate;
    private LocalTime resEndTime;

}