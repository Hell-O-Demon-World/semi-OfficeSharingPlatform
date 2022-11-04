package com.golfzonaca.officesharingplatform.repository.reservation;

import com.golfzonaca.officesharingplatform.domain.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository {

    List<Reservation> findAllByPlaceIdAndRoomKindIdAndDate(Long placeId, Long roomKindId,LocalDate reservationDate);
}
