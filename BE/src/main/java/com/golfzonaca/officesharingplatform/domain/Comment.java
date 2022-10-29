package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
@RequiredArgsConstructor
public class Comment {

    private AtomicLong id;
    private AtomicLong placeId;
    private String commentText;
    private String commentWriter;
    private LocalDateTime commentTime;
}
