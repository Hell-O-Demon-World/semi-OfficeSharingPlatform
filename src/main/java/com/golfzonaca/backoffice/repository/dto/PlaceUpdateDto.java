package com.golfzonaca.backoffice.repository.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceUpdateDto {

    private String placeName;
    private String placeDescription;
    private JsonObject businessDays;
    private LocalDateTime placeOpenTime;
    private LocalDateTime placeCloseTime;
    private JsonObject addInformation;
    private Long addressId; //확장이전 issue..
}