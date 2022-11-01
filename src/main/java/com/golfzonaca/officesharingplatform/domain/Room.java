package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class Room {

    private long id;
    private long placeTypeId;
    private long placeId;
    private long companyId;
    private int totalNumber;
    private Boolean roomState;
}
