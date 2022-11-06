package com.golfzonaca.officesharingplatform.service.reservation;

import com.golfzonaca.officesharingplatform.domain.Place;
import com.golfzonaca.officesharingplatform.domain.Reservation;
import com.golfzonaca.officesharingplatform.domain.Room;
import com.golfzonaca.officesharingplatform.repository.place.PlaceRepository;
import com.golfzonaca.officesharingplatform.repository.reservation.ReservationRepository;
import com.golfzonaca.officesharingplatform.repository.room.RoomRepository;
import com.golfzonaca.officesharingplatform.repository.roomkind.RoomKindRepository;
import com.golfzonaca.officesharingplatform.web.reservation.form.SelectedDateTimeForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class MyBatisReservationService implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final RoomKindRepository roomKindRepository;
    private final PlaceRepository placeRepository;

    private enum Weekdays {
        Mon(1), Tue(2), Wed(3), Thu(4), Fri(5), Sat(6), Sun(7);
        private final int description;

        Weekdays(int description) {
            this.description = description;
        }

        public int getDescription() {
            return description;
        }
    }

    @Override
    public List<Integer> getReservationTimeList(Long placeId, SelectedDateTimeForm selectedDateTimeForm) {
        if (placeRepository.findById(placeId) == null) {
            log.error("placeId 에 맞는 place가 없습니다.");
            return new ArrayList<>();
        }
        Place findPlace = placeRepository.findById(placeId);

        LocalDate reservationDate = toLocalDate(selectedDateTimeForm.getYear().toString()
                , selectedDateTimeForm.getMonth().toString(), selectedDateTimeForm.getDay().toString());
        String reservationDayOfWeek = reservationDate.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US);
        List<String> placeOpenList = Arrays.asList(findPlace.getPlaceOpendays().split(", "));

        Map<Integer, Boolean> inputTimeMap = getDefaultTimeMap();
        if (isOpenToday(reservationDayOfWeek, placeOpenList)) {
            Long roomKindId = roomKindRepository.findIdByRoomType(selectedDateTimeForm.getSelectedType());
            List<Room> reservationRoomList = roomRepository.findRoomByPlaceIdAndRoomKindId(placeId, roomKindId);
            //Reservation Table에서 해당 날짜에 대한 예약 정보를 불러옴
            List<Reservation> findReservationList = reservationRepository.findAllByPlaceIdAndRoomKindIdAndDate(placeId, roomKindId, reservationDate);

            int totalReservationCount = reservationRoomList.size();
            int beforeReservationCount = countBeforeReservationList(findReservationList);

            int startTime = findPlace.getPlaceStart().getHour();
            int endTime = findPlace.getPlaceEnd().getHour();

            //1-4 & 1-5 cnt == 0, empty list return
            if (hasFullReservation(totalReservationCount, beforeReservationCount)) {
                inputTimeMap = setStartTimeAndEndTime(inputTimeMap, startTime, endTime);
            } else {
                //2-2 get List Room
                for (Reservation reservation : findReservationList) {
                    for (int i = reservation.getResStartTime().getHour(); i < reservation.getResEndTime().getHour(); i++) {
                        if (inputTimeMap.get(i) == true) {
                            continue;
                        }
                        inputTimeMap.replace(i, false, true);
                    }
                }

                // 영업시간 설정
                for (int i = startTime; i < endTime; i++) {
                    if (inputTimeMap.get(i) == true) {
                        break;
                    }
                    inputTimeMap.replace(i, false, true);
                }
                for (int i = endTime - 1; i > startTime; i--) {
                    if (inputTimeMap.get(i) == true) {
                        break;
                    }
                    inputTimeMap.replace(i, false, true);
                }
            }
        }
        return parsingMapToList(inputTimeMap);
    }

    private List<Integer> parsingMapToList(Map<Integer, Boolean> inputTimeMap) {
        List<Integer> resultTimeList = new ArrayList<>();
        // map을 list로 변환
        Iterator<Map.Entry<Integer, Boolean>> itr = inputTimeMap.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<Integer, Boolean> entry = itr.next();
            if (entry.getValue() == true) {
                resultTimeList.add(entry.getKey());
            }
        }
        return resultTimeList;
    }

    private boolean hasFullReservation(int totalReservationCount, int beforeReservationCount) {
        boolean available = false;
        if (totalReservationCount - beforeReservationCount > 0) {
            available = true;
        }
        return available;
    }

    private Map<Integer, Boolean> setStartTimeAndEndTime(Map<Integer, Boolean> inputTimeMap, int startTime, int endTime) {
        if (endTime == 0) {
            endTime = 24;
        }
        for (int i = startTime; i < endTime; i++) {
            inputTimeMap.replace(i, false, true);
        }
        return inputTimeMap;
    }

    private int countBeforeReservationList(List<Reservation> findReservationList) {
//Set 변환 후 중복 제거
        //1-3 get beforeReservationCount
        Set<Long> countRoomIdSet = new HashSet<>();
        for (int i = 0; i < findReservationList.size(); i++) {
            countRoomIdSet.add(findReservationList.get(i).getRoomId());
        }
        return countRoomIdSet.size();
    }

    private Map<Integer, Boolean> getDefaultTimeMap() {
        Map<Integer, Boolean> timeMap = new HashMap<>();
        for (int i = 0; i < 24; i++) {
            timeMap.put(i, false);
        }
        return timeMap;
    }

    private Map<String, Boolean> getOpenDayMap(List<String> placeOpenList) {
        Map<String, Boolean> openDayMap = new LinkedHashMap<>();
        for (Weekdays item : Weekdays.values()) {
            if (placeOpenList.contains(item.toString())) {
                openDayMap.put(item.toString(), true);
            } else {
                openDayMap.put(item.toString(), false);
            }
        }
        return openDayMap;
    }

    private boolean isOpenToday(String reservationDayOfWeek, List<String> placeOpenList) {
        Map<String, Boolean> plcaeOpenMap = getOpenDayMap(placeOpenList);
        return plcaeOpenMap.get(reservationDayOfWeek);
    }

    public LocalDate toLocalDate(String year, String month, String day) {
        String StringType = String.format("%s, %s, %s", year, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu, M, d");
        LocalDate resDate = LocalDate.parse(StringType, formatter);
        return resDate;
    }

    @Override
    public void save(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findResByPlaceIdAndRoomKindId(long placeId, long roomTypeId, LocalDate resStartDate, LocalDate resEndDate) {
        return reservationRepository.findResByPlaceIdAndRoomKindId(placeId, roomTypeId, resStartDate, resEndDate);
    }

}
