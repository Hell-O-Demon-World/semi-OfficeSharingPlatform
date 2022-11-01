package com.golfzonaca.backoffice.repository.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class PlaceUpdateDto {

    private String placeName;
    private String placeDescription;
    private String placeOpenDays;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm:ss", timezone = "Asia/Seoul")
    private String placeStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm:ss", timezone = "Asia/Seoul")
    private String placeEnd;
    private String placeAddInfo;
    private Long addressId; //우편번호

    public PlaceUpdateDto() {
    }

    public PlaceUpdateDto(String placeName, String placeDescription, String placeOpenDays, String placeStart, String placeEnd, String placeAddInfo, Long addressId) {
        this.placeName = placeName;
        this.placeDescription = placeDescription;
        this.placeOpenDays = placeOpenDays;
        this.placeStart = placeStart;
        this.placeEnd = placeEnd;
        this.placeAddInfo = placeAddInfo;
        this.addressId = addressId;
    }
}