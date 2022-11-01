package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class Rating {

    private long id;
    private int score;
    private String review;
    private String assessmentWriter;
    private LocalDateTime writeTime;
}
