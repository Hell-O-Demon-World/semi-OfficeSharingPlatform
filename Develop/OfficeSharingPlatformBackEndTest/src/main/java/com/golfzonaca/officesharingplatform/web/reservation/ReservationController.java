package com.golfzonaca.officesharingplatform.web.reservation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.golfzonaca.officesharingplatform.config.auth.token.JwtManager;
import com.golfzonaca.officesharingplatform.domain.Reservation;
import com.golfzonaca.officesharingplatform.service.company.CompanyService;
import com.golfzonaca.officesharingplatform.service.reservation.ReservationService;
import com.golfzonaca.officesharingplatform.service.room.RoomService;
import com.golfzonaca.officesharingplatform.service.roomkind.RoomKindService;
import com.golfzonaca.officesharingplatform.service.user.UserService;
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
import java.time.format.TextStyle;
import java.util.*;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ReservationController {

    private final RoomKindService roomKindService;
    private final ReservationService reservationService;
    private final RoomService roomService;
    private final CompanyService companyService;
    private final UserService userService;

    @GetMapping("places/{placeId}")
    public JsonObject findRoom(@PathVariable long placeId) {

        List<Integer> meetingRoomList = new ArrayList<>();
        List<Integer> officeList = new ArrayList<>();
        JsonObject responseData = new JsonObject();

        List<Integer> findRoomTypeList = roomService.findRoomTypeByPlaceId(placeId);

        int deskQuantity = Collections.frequency(findRoomTypeList, 1);
        responseData.addProperty("desk", deskQuantity != 0);

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
    public Map book(@PathVariable long placeId, @RequestBody ResRequestData resRequestData) throws JsonProcessingException {

        Map<String, String> errorMap = new LinkedHashMap<>();
        Long userId = JwtManager.getIdByToken(resRequestData.getAccessToken());

        Boolean registeredStatus = userService.validateUserByUserId(userId);

        log.info("resRequestData={}", resRequestData);

        if (!registeredStatus) {
            errorMap.put("InvalidUserError", "???????????? ?????? ???????????????.");
            return errorMap;
        }

        Long roomTypeId = roomKindService.findIdByRoomType(resRequestData.getSelectedType());

        if (roomTypeId == -1) {
            errorMap.put("NonexistentRoomTypeError", "???????????? ????????? ???????????? ????????????.");
            return errorMap;
        }

        int roomQuantity = roomService.countRoomQuantityByPlaceId(placeId, roomTypeId);

        int resCount = 0;

        LocalDate resStartDate = toLocalDate(resRequestData.getYear(), resRequestData.getMonth(), resRequestData.getDay());
        LocalDate resEndDate = toLocalDate(resRequestData.getYear(), resRequestData.getMonth(), resRequestData.getDay());
        LocalTime resStartTime = toLocalTime(resRequestData.getStartTime());
        LocalTime resEndTime = toLocalTime(resRequestData.getEndTime());
        String resStartDayOfWeek = resStartDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US);
        String resEndDayOfWeek = resEndDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US);
        
        if (!(companyService.findOpenDaysById(placeId).contains(resStartDayOfWeek)) || !(companyService.findOpenDaysById(placeId).contains(resEndDayOfWeek))) {
            errorMap.put("InvalidOpenDaysError", "???????????? ????????? ??????????????????.");
            return errorMap;
        }

        if (resStartTime.equals(resEndTime)) {
            errorMap.put("InvalidResTimeError", "?????? 1?????? ?????? ?????? ???????????????.");
            return errorMap;
        }

        if (resStartDate.atTime(resStartTime).isBefore(LocalDateTime.now()) || resEndDate.atTime(resEndTime).isBefore(LocalDateTime.now())) {
            errorMap.put("PastDateTimeError", "??????????????? ???????????? ???????????????.");
            return errorMap;
        }

        if (roomQuantity == 0) {
            errorMap.put("NotExistedRoomError", "???????????? Place ??? ???????????? ?????? ???????????????.");
            return errorMap;
        }

        List<Long> findRoomIdList = roomService.findRoomIdByPlaceIdAndRoomTypeId(placeId, roomTypeId);

        List<Reservation> findResList = reservationService.findResByPlaceIdAndRoomKindId(placeId, roomTypeId, resStartDate, resEndDate);

        if (findResList.size() == 0) {
            Reservation reservation = new Reservation(placeId, userId, findRoomIdList.get(0), roomTypeId, resStartDate, resStartTime, resEndDate, resEndTime);
            reservationService.save(reservation);
            return errorMap;
        }

        for (Reservation reservation : findResList) {
            if ((reservation.getResStartTime().isBefore(resStartTime) || reservation.getResStartTime().equals(resStartTime)) && reservation.getResEndTime().isAfter(resStartTime)) {
                if (reservation.getUserId() != userId) {
                    resCount++;
                    findRoomIdList.remove(reservation.getRoomId());
                } else {
                    errorMap.put("DuplicatedResForUserError", "???????????? ????????? ????????? ?????? ?????? ????????? ???????????????.");
                    return errorMap;
                }
            } else if (resStartTime.isBefore(reservation.getResStartTime()) && resEndTime.isAfter(reservation.getResStartTime())) {
                if (reservation.getUserId() != userId) {
                    resCount++;
                    findRoomIdList.remove(reservation.getRoomId());
                } else {
                    errorMap.put("DuplicatedResForUserError", "???????????? ????????? ????????? ?????? ?????? ????????? ???????????????.");
                    return errorMap;
                }
            }
        }
        if (roomQuantity == resCount) {
            errorMap.put("DuplicatedResForRoomError", "?????? Place ??? ???????????? ????????? ??????????????? ??????????????? ????????????.");
            return errorMap;
        }
        Reservation reservation = new Reservation(placeId, userId, findRoomIdList.get(0), roomTypeId, resStartDate, resStartTime, resEndDate, resEndTime);
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

