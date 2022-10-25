package com.golfzonaca.officesharingplatform.web.search;

import lombok.Data;

@Data
public class ResultData {
    private String PlaceName;
    private String PlaceAddress;
    private Boolean Wifi;
    private Boolean AlwaysOpen;
    private Boolean ManageDirect;
}
