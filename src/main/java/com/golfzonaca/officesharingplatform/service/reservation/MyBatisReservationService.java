package com.golfzonaca.officesharingplatform.service.reservation;

import com.golfzonaca.officesharingplatform.domain.Reservation;
import com.golfzonaca.officesharingplatform.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyBatisReservationService implements ReservationService {

    private final ReservationRepository reservationRepository;

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public int findIdBySelectedType(String selectedType) {
        return reservationRepository.findIdBySelectedType(selectedType);
    }

    @Override
    public List<Long> findRoomIdByPlaceIdAndRoomTypeId(long placeId, long roomTypeId) {
        return reservationRepository.findRoomIdByPlaceIdAndRoomTypeId(placeId, roomTypeId);
    }

    @Override
    public int countRoomQuantityByPlaceId(long placeId, long roomTypeId) {
        return reservationRepository.countRoomQuantityByPlaceId(placeId, roomTypeId);
    }

    @Override
    public List<Reservation> findResByPlaceIdAndRoomKindId(long placeId, long roomTypeId, LocalDate resStartDate, LocalDate resEndDate) {
        return reservationRepository.findResByPlaceIdAndRoomKindId(placeId, roomTypeId, resStartDate, resEndDate);
    }

    @Override
    public List<Integer> findRoomTypeByPlaceId(long placeId) {
        return reservationRepository.findRoomTypeByPlaceId(placeId);
    }
}
