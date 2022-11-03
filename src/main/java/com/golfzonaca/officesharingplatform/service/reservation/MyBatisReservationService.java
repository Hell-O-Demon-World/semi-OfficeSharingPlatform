package com.golfzonaca.officesharingplatform.service.reservation;

import com.golfzonaca.officesharingplatform.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyBatisReservationService implements ReservationService {

    private final ReservationRepository reservationRepository;
}
