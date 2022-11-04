package com.golfzonaca.officesharingplatform.service.reservation;

import com.golfzonaca.officesharingplatform.web.reservation.form.SelectedDateTimeForm;

import java.util.List;

public interface ReservationService {
    List<Integer> getReservationTimeList(Long placeId, SelectedDateTimeForm selectedDateTimeForm);
}
