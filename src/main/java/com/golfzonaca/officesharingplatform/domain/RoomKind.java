package com.golfzonaca.officesharingplatform.domain;

import com.golfzonaca.officesharingplatform.domain.type.ProductType;
import lombok.Data;

import java.util.concurrent.atomic.AtomicLong;

@Data
public class RoomKind {
    private AtomicLong id;
    private ProductType productType;
    private int price;
}
