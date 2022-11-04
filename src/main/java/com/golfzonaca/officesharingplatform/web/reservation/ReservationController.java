package com.golfzonaca.officesharingplatform.web.reservation;

import com.golfzonaca.officesharingplatform.domain.Reservation;
import com.golfzonaca.officesharingplatform.service.reservation.ReservationService;
import com.golfzonaca.officesharingplatform.web.reservation.form.ResRequestData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping("places/{placeId}/book")
    public Map Book(@PathVariable long placeId, @RequestBody ResRequestData resRequestData) {

        int roomTypeId = reservationService.findIdBySelectedType(resRequestData.getSelectedType());

        int roomQuantity = reservationService.countRoomQuantityByPlaceId(placeId, roomTypeId);

        int resCount = 0;

        Map<String, String> errorMap = new LinkedHashMap<>();

        LocalDate resStartDate = toLocalDate(resRequestData.getYear(), resRequestData.getMonth(), resRequestData.getDay());
        LocalDate resEndDate = toLocalDate(resRequestData.getYear(), resRequestData.getMonth(), resRequestData.getDay());
        LocalTime resStartTime = toLocalTime(resRequestData.getStartTime());
        LocalTime resEndTime = toLocalTime(resRequestData.getEndTime());

        if (resStartDate.atTime(resStartTime).isBefore(LocalDateTime.now()) || resEndDate.atTime(resEndTime).isBefore(LocalDateTime.now())) {
            errorMap.put("PastDateTimeError", "예약일시가 현재보다 과거입니다.");
            return errorMap;
        }

        if (roomQuantity == 0) {
            log.info("존재하지 않는 타입의 공간입니다.");
            errorMap.put("NotExistedRoomError", "존재하지 않는 공간입니다.");
            return errorMap;
        }

        List<Long> findRoomIdList = reservationService.findRoomIdByPlaceIdAndRoomTypeId(placeId, roomTypeId);

        List<Reservation> findResList = reservationService.findResByPlaceIdAndRoomKindId(placeId, roomTypeId, resStartDate, resEndDate);

        if (findResList.size() == 0) {
            Reservation reservation = new Reservation(placeId, resRequestData.getUserId(), findRoomIdList.get(0), roomTypeId, resStartDate, resStartTime, resEndDate, resEndTime);
            reservationService.save(reservation);
            log.info("예약 성공");
            return errorMap;
        }

        for (Reservation reservation : findResList) {
            if ((reservation.getResStartTime().isBefore(resStartTime) || reservation.getResStartTime().equals(resStartTime)) && reservation.getResEndTime().isAfter(resStartTime)) {
                resCount++;
                findRoomIdList.remove(Long.valueOf(reservation.getRoomId()));
            } else if (resStartTime.isBefore(reservation.getResStartTime()) && resEndTime.isAfter(reservation.getResStartTime())) {
                resCount++;
                findRoomIdList.remove(Long.valueOf(reservation.getRoomId()));
            }
        }
        log.info("resCount={}", resCount);
        if (roomQuantity == resCount) {
            log.info("해당 시간에 모든 " + resRequestData.getSelectedType() + " 이 예약되어 있습니다.");
            errorMap.put("DuplicatedResError", "이미 에약된 공간입니다.");
            return errorMap;
        }
        Reservation reservation = new Reservation(placeId, resRequestData.getUserId(), findRoomIdList.get(0), roomTypeId, resStartDate, resStartTime, resEndDate, resEndTime);
        reservationService.save(reservation);
        log.info("예약 성공");
        return errorMap;
    }

    public LocalDate toLocalDate(String year, String month, String day) {
        String StringType = String.format("%s, %s, %s", year, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu, M, d");
        LocalDate resDate = LocalDate.parse(StringType, formatter);
        return resDate;
    }

    public LocalTime toLocalTime(String hour) {
        String StringType = String.format("%s, %s, %s", hour, "0", "0");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H, m, s");
        LocalTime resTime = LocalTime.parse(StringType, formatter);
        return resTime;
    }
}

