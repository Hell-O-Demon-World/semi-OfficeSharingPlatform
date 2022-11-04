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

    @Override
    public void save(Reservation reservation) {
        reservationMapper.save(reservation);
    }

    @Override
    public int findIdBySelectedType(String selectedType) {
        Integer typeId = reservationMapper.findIdBySelectedType(selectedType);
        if (typeId == null) {
            return -1;
        }
        return typeId;
    }

    @Override
    public List<Long> findRoomIdByPlaceIdAndRoomTypeId(long placeId, long roomTypeId) {
        return reservationMapper.findRoomIdByPlaceIdAndRoomTypeId(placeId, roomTypeId);
    }

    @Override
    public int countRoomQuantityByPlaceId(long placeId, long roomTypeId) {
        return reservationMapper.countRoomQuantityByPlaceId(placeId, roomTypeId);
    }

    @Override
    public List<Reservation> findResByPlaceIdAndRoomKindId(long placeId, long roomTypeId, LocalDate resStartDate, LocalDate resEndDate) {
        return reservationMapper.findResByPlaceIdAndRoomKindId(placeId, roomTypeId, resStartDate, resEndDate);
    }

    @Override
    public List<Integer> findRoomTypeByPlaceId(long placeId) {
        return reservationMapper.findRoomTypeByPlaceId(placeId);
    }
}
