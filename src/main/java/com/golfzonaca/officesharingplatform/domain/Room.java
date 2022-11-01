package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Room {

    private long id;
    private long roomKindId;
    private long placeId;
    private long companyId;
    private int totalNum;
    private boolean roomState;
}
