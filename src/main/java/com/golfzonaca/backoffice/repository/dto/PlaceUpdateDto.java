package com.golfzonaca.backoffice.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceUpdateDto {

    private String placeName;
    private String placeDescription;
    private JSONObject businessDays;
    private LocalTime placeOpenTime;
    private LocalTime placeCloseTime;
    private JSONObject addInformation;
    private Long addressId; //확장이전 issue..
}