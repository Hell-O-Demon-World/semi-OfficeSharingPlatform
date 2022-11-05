package com.golfzonaca.officesharingplatform.service.mypage;

import com.golfzonaca.officesharingplatform.domain.Reservation;
import com.golfzonaca.officesharingplatform.repository.reservation.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyBatisMyPageService implements MyPageService{
    private final ReservationRepository reservationRepository;

    @Override
    public void cancelByOrderAndUserId(Integer order, Long userId) {
        List<Reservation> reservationList = reservationRepository.findAllByUserId(userId);
        System.out.println("reservationList = " + reservationList);
        reservationRepository.deleteById( reservationList.get(order - 1).getId());
    }
}
