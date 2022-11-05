package com.golfzonaca.backoffice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    private long id;
    private long roomKindId;
    private long placeId;
    private long companyId;
    private int totalNum;

    public Room(long roomKindId, long placeId, long companyId, int totalNum) {
        this.roomKindId = roomKindId;
        this.placeId = placeId;
        this.companyId = companyId;
        this.totalNum = totalNum;
    }
}
