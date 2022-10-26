package com.golfzonaca.backoffice.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Place {

    private Long id; //대여공간식별번호
    private Long companyId; //업체식별번호
    private String name;
    private String description;
    private Map<String, Boolean> placeOpen;
    private LocalDateTime placeStartTime;
    private LocalDateTime placeEndTime;
    private Map<String, Boolean> placeAddInfo;
    private int addressId; //우편번호

    public Place() {
    }

    public Place(Long companyId, String name, String description, Map<String, Boolean> placeOpen, LocalDateTime placeStartTime, LocalDateTime placeEndTime, Map<String, Boolean> placeAddInfo, int addressId) {
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
