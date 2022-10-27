package com.golfzonaca.backoffice.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceUpdateDto {

    private String placeName;
    private String placeDescription;
    private JSONObject businessDays;
    private LocalDateTime placeOpenTime;
    private LocalDateTime placeCloseTime;
    private JSONObject addInformation;
    private Long addressId; //확장이전 issue..
}