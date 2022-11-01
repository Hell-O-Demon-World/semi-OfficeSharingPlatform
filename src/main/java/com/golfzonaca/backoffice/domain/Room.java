package com.golfzonaca.backoffice.domain;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class Room {

    private long id;
    private long roomKindId;
    private long placeId;
    private long companyId;
    private int totalNum;
    private Boolean roomState;
}
