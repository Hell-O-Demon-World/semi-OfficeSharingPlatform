package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
@RequiredArgsConstructor
public class RoomPhoto {

    private AtomicLong roomId;
    private AtomicLong photoId;
    private AtomicLong typeId;
    private AtomicLong companyId;
}
