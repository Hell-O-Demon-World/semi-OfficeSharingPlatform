package com.golfzonaca.backoffice.domain;

import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
public class Room {

    private Long id;
    private Long roomKindId;
    private Long placeId;
    private Long companyId;
    private int totalNumber;
    private Boolean roomState;
}
