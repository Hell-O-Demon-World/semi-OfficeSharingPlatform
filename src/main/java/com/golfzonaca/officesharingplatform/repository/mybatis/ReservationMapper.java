package com.golfzonaca.officesharingplatform.repository.mybatis;

import com.golfzonaca.officesharingplatform.domain.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReservationMapper {

    List<Reservation> findAllByPlaceIdAndRoomKindIdAndDate(long placeId, long roomKindId, LocalDate reservationDate);
}
