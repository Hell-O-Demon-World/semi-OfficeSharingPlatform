package com.golfzonaca.officesharingplatform.web.reservation;

import com.golfzonaca.officesharingplatform.domain.Reservation;
import com.golfzonaca.officesharingplatform.service.reservation.ReservationService;
import com.golfzonaca.officesharingplatform.web.reservation.form.ResRequestData;
import com.golfzonaca.officesharingplatform.web.reservation.form.SelectedDateTimeForm;
import com.golfzonaca.officesharingplatform.web.reservation.form.TimeListForm;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        List<Integer> meetingRoomList = new ArrayList<>();
        List<Integer> officeList = new ArrayList<>();
        JsonObject responseData = new JsonObject();

        List<Integer> findRoomTypeList = reservationService.findRoomTypeByPlaceId(placeId);

        int deskQuantity = Collections.frequency(findRoomTypeList, 1);
        if (deskQuantity != 0) {
            responseData.addProperty("desk", true);
        } else {
            responseData.addProperty("desk", false);
        }

        for (int i = 0; i < 5; i++) {
            if (Collections.frequency(findRoomTypeList, i + 1) != 0) {
                if (i + 1 == 2) {
                    meetingRoomList.add(4);
                } else if (i + 1 == 3) {
                    meetingRoomList.add(6);
                } else if (i + 1 == 4) {
                    meetingRoomList.add(10);
                } else if (i + 1 == 5) {
                    meetingRoomList.add(20);
                }
            }
        }
        String meetingRoom = new Gson().toJson(meetingRoomList);
        responseData.addProperty("meetingRoom", meetingRoom);

        for (int i = 0; i < 4; i++) {
            if (Collections.frequency(findRoomTypeList, i + 6) != 0) {
                if (i + 6 == 6) {
                    officeList.add(20);
                } else if (i + 6 == 7) {
                    officeList.add(40);
                } else if (i + 6 == 8) {
                    officeList.add(70);
                } else {
                    officeList.add(100);
                }
            }
        }

        String office = new Gson().toJson(officeList);
        responseData.addProperty("office", office);
        return responseData;
    }

    @PostMapping("/places/{placeId}")
    public TimeListForm selectedDateTime(@PathVariable String placeId, @Valid @RequestBody SelectedDateTimeForm selectedDateTimeForm, BindingResult bindingResult) {
        TimeListForm timeListForm = new TimeListForm();
        timeListForm.setTimeList(reservationService.getReservationTimeList(Long.parseLong(placeId), selectedDateTimeForm));
        return timeListForm;
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
                    findRoomIdList.remove(reservation.getRoomId());
                } else {
                    errorMap.put("DuplicatedResForUserError", "선택하신 공간에 대한 예약 내역이 존재합니다.");
                    return errorMap;
                }
            } else if (resStartTime.isBefore(reservation.getResStartTime()) && resEndTime.isAfter(reservation.getResStartTime())) {
                if (reservation.getUserId() != resRequestData.getUserId()) {
                    resCount++;
                    findRoomIdList.remove(reservation.getRoomId());
                } else {
                    errorMap.put("DuplicatedResForUserError", "선택하신 공간에 대한 예약 내역이 존재합니다.");
                    return errorMap;
                }
            }
        }
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
        return LocalDate.parse(StringType, formatter);
    }

    public LocalTime toLocalTime(String hour) {
        String StringType = String.format("%s, %s, %s", hour, "0", "0");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H, m, s");
        return LocalTime.parse(StringType, formatter);
    }
}

