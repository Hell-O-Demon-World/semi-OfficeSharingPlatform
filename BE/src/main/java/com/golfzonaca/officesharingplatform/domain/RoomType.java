package com.golfzonaca.officesharingplatform.domain;

import com.golfzonaca.officesharingplatform.domain.type.ProductType;

import java.util.concurrent.atomic.AtomicLong;

public class RoomType {
    private AtomicLong id;
    private ProductType productType;
    private int price;
}
