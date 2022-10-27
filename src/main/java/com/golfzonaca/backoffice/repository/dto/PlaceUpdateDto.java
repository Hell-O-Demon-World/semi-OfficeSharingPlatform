package com.golfzonaca.backoffice.repository.dto;

import com.golfzonaca.backoffice.domain.type.DaysType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceUpdateDto {

    private String placeName;
    private String placeDescription;
    private DaysType businessDays;
    private LocalTime placeOpenTime;
    private LocalTime placeCloseTime;
    private String addInformation;
    private Long addressId; //확장이전 issue..
}