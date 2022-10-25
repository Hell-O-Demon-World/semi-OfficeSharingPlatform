package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
@RequiredArgsConstructor
public class UserRating {

    private AtomicLong userId;
    private AtomicLong ratingId;
    private AtomicLong mileageId;
}
