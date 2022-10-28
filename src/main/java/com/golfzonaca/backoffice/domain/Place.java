package com.golfzonaca.backoffice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.golfzonaca.backoffice.domain.type.DaysType;
import lombok.Data;

@Data
public class Place {

    private Long id; //대여공간식별번호
    private Long companyId; //업체식별번호
    private String placeName;
    private String placeDescription;
    private DaysType placeOpenDays;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm:ss", timezone = "Asia/Seoul")
    private String placeStart;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "kk:mm:ss", timezone = "Asia/Seoul")
    private String placeEnd;
    private String placeAddInfo;
    private Long addressId; //우편번호
    public Place() {
    }

    public Place(Long companyId, String placeName, String placeDescription, DaysType placeOpenDays, String placeStart, String placeEnd, String placeAddInfo, Long addressId) {
        this.companyId = companyId;
        this.placeName = placeName;
        this.placeDescription = placeDescription;
        this.placeOpenDays = placeOpenDays;
        this.placeStart = placeStart;
        this.placeEnd = placeEnd;
        this.placeAddInfo = placeAddInfo;
        this.addressId = addressId;
    }
}