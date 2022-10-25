package com.golfzonaca.officesharingplatform.web.search;

import lombok.Data;

@Data
public class RequestData {

    private String Keyword;
    private Boolean Desk;
    private Boolean Conference;
    private Boolean Office;
}
