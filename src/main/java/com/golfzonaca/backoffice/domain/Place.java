package com.golfzonaca.backoffice.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.json.JSONObject;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;

@Data
public class Place {

    private Long id; //대여공간식별번호
    private Long companyId; //업체식별번호
    private String name;
    private String description;
    private JSONObject placeOpen;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm:ss", timezone = "Asia/Seoul")
    private LocalTime placeStartTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm:ss", timezone = "Asia/Seoul")
    private LocalTime placeEndTime;
    private JSONObject placeAddInfo;
    private Long addressId; //우편번호

    public Place() {
    }

    public Place(Long companyId, String name, String description, JSONObject placeOpen, LocalTime placeStartTime, LocalTime placeEndTime, JSONObject placeAddInfo, Long addressId) {
        this.companyId = companyId;
        this.name = name;
        this.description = description;
        this.placeOpen = placeOpen;
        this.placeStartTime = placeStartTime;
        this.placeEndTime = placeEndTime;
        this.placeAddInfo = placeAddInfo;
        this.addressId = addressId;
    }
}
