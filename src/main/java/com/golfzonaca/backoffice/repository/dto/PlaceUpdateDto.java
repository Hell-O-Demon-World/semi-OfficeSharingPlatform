package com.golfzonaca.backoffice.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceUpdateDto {

    private String placeName;
    private String placeDescription;
    private String placeOpenDays;
    private String placeStart;
    private String placeEnd;
    private String placeAddInfo;
    private Long addressId; //확장이전 issue..
}