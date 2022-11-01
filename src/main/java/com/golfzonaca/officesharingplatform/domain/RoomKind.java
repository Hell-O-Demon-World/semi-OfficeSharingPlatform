package com.golfzonaca.officesharingplatform.domain;

import com.golfzonaca.officesharingplatform.domain.type.RoomType;
import lombok.Data;

@Data
public class RoomKind {
    private long id;
    private RoomType roomType;
    private int price;
}
