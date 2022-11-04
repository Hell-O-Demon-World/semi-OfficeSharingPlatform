package com.golfzonaca.officesharingplatform.repository.reservation;

import com.golfzonaca.officesharingplatform.domain.Reservation;
import com.golfzonaca.officesharingplatform.repository.mybatis.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MyBatisReservationRepository implements ReservationRepository {

    private final ReservationMapper reservationMapper;

    @Override
    public List<Reservation> findAllByPlaceIdAndRoomKindIdAndDate(Long placeId, Long roomKindId, LocalDate reservationDate) {
        return reservationMapper.findAllByPlaceIdAndRoomKindIdAndDate(placeId, roomKindId, reservationDate);
    }
}
