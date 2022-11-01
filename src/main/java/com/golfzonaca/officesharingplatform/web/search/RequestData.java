package com.golfzonaca.officesharingplatform.web.search;

import lombok.Data;

@Data
public class RequestData {

    private String keyword;
    private Boolean desk;
    private Boolean conference;
    private Boolean office;
}
