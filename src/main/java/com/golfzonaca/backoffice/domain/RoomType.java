package com.golfzonaca.backoffice.domain;

import com.golfzonaca.backoffice.domain.type.ProductType;

import java.util.concurrent.atomic.AtomicLong;

public class RoomType {
    private AtomicLong id;
    private ProductType productType;
    private int price;
}