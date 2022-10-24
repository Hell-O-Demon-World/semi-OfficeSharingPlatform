package com.golfzonaca.officesharingplatform.domain;

<<<<<<< Updated upstream
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Getter
@ToString
@RequiredArgsConstructor
public class Place {

    private AtomicLong id; //대여공간식별번호
    private AtomicLong companyId; //업체식별번호
    private String name;
    private String description;
    private Map<String, Boolean> placeOpen;
    private LocalDateTime placeStartTime;
    private LocalDateTime placeEndTime;
    private Map<String, Boolean> placeAddInfo;
    private int postalCode; //우편번호
}
=======
import java.sql.Time;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class Place {
    private AtomicLong id;
    private AtomicLong companyId;
    private String placeName;
    private String description;
    private Map<String, Boolean> businessDays;
    private Time startTime;
    private Time endTime;
    private Map<String, Boolean> information;
    private AtomicLong addressId;
}
>>>>>>> Stashed changes
