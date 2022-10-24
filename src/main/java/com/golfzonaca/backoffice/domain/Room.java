package com.golfzonaca.backoffice.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
@RequiredArgsConstructor
public class Room {

    private AtomicLong id;
    private AtomicLong placeTypeId;
    private AtomicLong placeId;
    private AtomicLong companyId;
    private int totalNumber;
    private Boolean roomState;
}
