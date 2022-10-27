package com.golfzonaca.backoffice.repository.dto;

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
    private String businessDays;
    private String placeOpenTime;
    private String placeCloseTime;
    private String addInformation;
    private Long addressId; //확장이전 issue..
}