package com.golfzonaca.backoffice.repository.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.golfzonaca.backoffice.domain.type.DaysType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceUpdateDto {

    private String placeName;
    private String placeDescription;
    private DaysType placeOpenDays;
    private String placeStart;
    private String placeEnd;
    private String placeAddInfo;
    private Long addressId; //확장이전 issue..
}