package com.golfzonaca.officesharingplatform.service.reservation;

import com.golfzonaca.officesharingplatform.domain.Reservation;
import com.golfzonaca.officesharingplatform.web.reservation.form.SelectedDateTimeForm;

import java.time.LocalDate;
import java.util.List;

public interface ReservationService {

    List<Integer> getReservationTimeList(Long placeId, SelectedDateTimeForm selectedDateTimeForm);

    void save(Reservation reservation);

    int findIdBySelectedType(String selectedType);

    List<Long> findRoomIdByPlaceIdAndRoomTypeId(long placeId, long RoomTypeId);

    int countRoomQuantityByPlaceId(long placeId, long roomTypeId);

    List<Reservation> findResByPlaceIdAndRoomKindId(long placeId, long roomTypeId, LocalDate resStartDate, LocalDate resEndDate);

    List<Integer> findRoomTypeByPlaceId(long placeId);
}
