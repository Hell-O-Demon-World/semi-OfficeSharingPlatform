package com.golfzonaca.officesharingplatform.web.reservation;

import com.golfzonaca.officesharingplatform.domain.Reservation;
import com.golfzonaca.officesharingplatform.service.reservation.ReservationService;
import com.golfzonaca.officesharingplatform.web.reservation.form.ResRequestData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("places/{placeId}")
    public JsonObject findRoom(@PathVariable long placeId) {

        List<Integer> findRoomTypeList = reservationService.findRoomTypeByPlaceId(placeId);
        log.info("findRoomTypeList={}", findRoomTypeList);

        List<Integer> meetingRoom = new ArrayList<>();
        List<Integer> office = new ArrayList<>();
        JsonObject responseData = new JsonObject();

        int deskQuantity = Collections.frequency(findRoomTypeList, 1);
        int meetingRoom4 = Collections.frequency(findRoomTypeList, 2);
        int meetingRoom6 = Collections.frequency(findRoomTypeList, 3);
        int meetingRoom10 = Collections.frequency(findRoomTypeList, 4);
        int meetingRoom20 = Collections.frequency(findRoomTypeList, 5);
        int office20 = Collections.frequency(findRoomTypeList, 6);
        int office40 = Collections.frequency(findRoomTypeList, 7);
        int office70 = Collections.frequency(findRoomTypeList, 8);
        int office100 = Collections.frequency(findRoomTypeList, 9);

        if (deskQuantity != 0) {
            responseData.addProperty("desk", true);
        } else {
            responseData.addProperty("desk", false);
        }

        for (int i = 0; i < 5; i++) {
            if (Collections.frequency(findRoomTypeList, i + 1) != 0) {
                if (i + 1 == 2) {
                    meetingRoom.add(4);
                } else if (i + 1 == 3) {
                    meetingRoom.add(6);
                } else if (i + 1 == 4) {
                    meetingRoom.add(10);
                } else if (i + 1 == 5) {
                    meetingRoom.add(20);
                }
            }
        }
        String meetingRoomL = new Gson().toJson(meetingRoom);
        responseData.addProperty("meetingRoom", meetingRoomL);

        for (int i = 0; i < 4; i++) {
            if (Collections.frequency(findRoomTypeList, i + 6) != 0) {
                if (i + 6 == 6) {
                    office.add(20);
                } else if (i + 6 == 7) {
                    office.add(40);
                } else if (i + 6 == 8) {
                    office.add(70);
                } else {
                    office.add(100);
                }
            }
        }

        String officeL = new Gson().toJson(office);
        responseData.addProperty("office", officeL);

        log.info("responseData={}", responseData);

        return responseData;
    }

    @PostMapping("places/{placeId}/book")
    public Map book(@PathVariable long placeId, @RequestBody ResRequestData resRequestData) {

        Map<String, String> errorMap = new LinkedHashMap<>();

        int roomTypeId = reservationService.findIdBySelectedType(resRequestData.getSelectedType());

        if (roomTypeId == -1) {
            errorMap.put("NonexistentRoomTypeError", "선택하신 타입은 존재하지 않습니다.");
            return errorMap;
        }

        int roomQuantity = reservationService.countRoomQuantityByPlaceId(placeId, roomTypeId);

        int resCount = 0;

        LocalDate resStartDate = toLocalDate(resRequestData.getYear(), resRequestData.getMonth(), resRequestData.getDay());
        LocalDate resEndDate = toLocalDate(resRequestData.getYear(), resRequestData.getMonth(), resRequestData.getDay());
        LocalTime resStartTime = toLocalTime(resRequestData.getStartTime());
        LocalTime resEndTime = toLocalTime(resRequestData.getEndTime());

        if (resStartDate.atTime(resStartTime).isBefore(LocalDateTime.now()) || resEndDate.atTime(resEndTime).isBefore(LocalDateTime.now())) {
            errorMap.put("PastDateTimeError", "예약일시가 현재보다 과거입니다.");
            return errorMap;
        }

        if (roomQuantity == 0) {
            errorMap.put("NotExistedRoomError", "선택하신 Place 에 준비되지 않은 공간입니다.");
            return errorMap;
        }

        List<Long> findRoomIdList = reservationService.findRoomIdByPlaceIdAndRoomTypeId(placeId, roomTypeId);

        List<Reservation> findResList = reservationService.findResByPlaceIdAndRoomKindId(placeId, roomTypeId, resStartDate, resEndDate);

        if (findResList.size() == 0) {
            Reservation reservation = new Reservation(placeId, resRequestData.getUserId(), findRoomIdList.get(0), roomTypeId, resStartDate, resStartTime, resEndDate, resEndTime);
            reservationService.save(reservation);
            return errorMap;
        }

        for (Reservation reservation : findResList) {
            if ((reservation.getResStartTime().isBefore(resStartTime) || reservation.getResStartTime().equals(resStartTime)) && reservation.getResEndTime().isAfter(resStartTime)) {
                if (reservation.getUserId() != resRequestData.getUserId()) {
                    resCount++;
                    findRoomIdList.remove(Long.valueOf(reservation.getRoomId()));
                } else {
                    errorMap.put("DuplicatedResForUserError", "선택하신 공간에 대한 예약 내역이 존재합니다.");
                    return errorMap;
                }
            } else if (resStartTime.isBefore(reservation.getResStartTime()) && resEndTime.isAfter(reservation.getResStartTime())) {
                if (reservation.getUserId() != resRequestData.getUserId()) {
                    resCount++;
                    findRoomIdList.remove(Long.valueOf(reservation.getRoomId()));
                } else {
                    errorMap.put("DuplicatedResForUserError", "선택하신 공간에 대한 예약 내역이 존재합니다.");
                    return errorMap;
                }
            }
        }
        log.info("resCount={}", resCount);
        if (roomQuantity == resCount) {
            errorMap.put("DuplicatedResForRoomError", "선택하신 타입의 이용가능한 공간이 없습니다.");
            return errorMap;
        }
        Reservation reservation = new Reservation(placeId, resRequestData.getUserId(), findRoomIdList.get(0), roomTypeId, resStartDate, resStartTime, resEndDate, resEndTime);
        reservationService.save(reservation);
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

