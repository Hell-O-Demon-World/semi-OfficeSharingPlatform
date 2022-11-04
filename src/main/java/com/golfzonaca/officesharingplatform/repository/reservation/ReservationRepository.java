package com.golfzonaca.officesharingplatform.repository.reservation;

import com.golfzonaca.officesharingplatform.domain.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository {

    void save(Reservation reservation);

    int findIdBySelectedType(String selectedType);

    List<Long> findRoomIdByPlaceIdAndRoomTypeId(long placeId, long roomTypeId);

    int countRoomQuantityByPlaceId(long placeId, long roomTypeId);

    List<Reservation> findResByPlaceIdAndRoomKindId(long placeId, long roomTypeId, LocalDate resStartDate, LocalDate resEndDate);
}
