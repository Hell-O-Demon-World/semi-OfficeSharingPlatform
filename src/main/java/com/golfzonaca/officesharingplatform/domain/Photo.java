package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
@RequiredArgsConstructor
public class Photo {

    private AtomicLong id;
    private String fileName;
    private String path;
}
