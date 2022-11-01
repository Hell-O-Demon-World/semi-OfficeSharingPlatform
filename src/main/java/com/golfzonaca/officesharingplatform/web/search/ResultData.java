package com.golfzonaca.officesharingplatform.web.search;

import lombok.Data;

@Data
public class ResultData {
    private String placeName;
    private String placeAddress;
    private Boolean wifi;
    private Boolean alwaysOpen;
    private Boolean manageDirect;
}
