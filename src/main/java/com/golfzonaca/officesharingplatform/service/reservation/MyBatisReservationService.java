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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MyBatisReservationService implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    private final RoomKindRepository roomKindRepository;
    private final PlaceRepository placeRepository;

    @Override
    public List<Integer> getReservationTimeList(Long placeId, SelectedDateTimeForm selectedDateTimeForm) {
        List<Integer> resultTimeList = new ArrayList<>();
        Place findPlace = placeRepository.findById(placeId);

        // form에 저장된 값을 localdate로 변환
        LocalDate reservationDate = toLocalDate(selectedDateTimeForm.getYear().toString()
                , selectedDateTimeForm.getMonth().toString(), selectedDateTimeForm.getDay().toString());
        //1.
        //1-1 get room_kind_id
        Long roomKindId = roomKindRepository.findIdByRoomType(selectedDateTimeForm.getSelectedType());
        //1-2 get totalReservationCount
        List<Room> reservationRoomList = roomRepository.findRoomByPlaceIdAndRoomKindId(placeId, roomKindId);
        int totalReservationCount = reservationRoomList.size();
        //Reservation Table에서 해당 날짜에 대한 예약 정보를 불러옴
        List<Reservation> findReservationList = reservationRepository.findAllByPlaceIdAndRoomKindIdAndDate(placeId, roomKindId, reservationDate);
        //1-3 get beforeReservationCount
        int beforeReservationCount = findReservationList.size();
        //1-4 & 1-5 cnt == 0, empty list return
        if (totalReservationCount - beforeReservationCount == 0) {
            return new ArrayList<>();
        }

        //2.
        // 2-1 장소에 대한 시작시간 종료 시간 가져옴
        int startTime = findPlace.getPlaceStart().getHour();
        int endTime = findPlace.getPlaceEnd().getHour();
        // 이용 할 시간 삽입
        Map<Integer, Boolean> inputTimeMap = getDefaultTimeMap();

        //2-2 get List Room
        for (Reservation reservation : findReservationList) {
            for (int i = reservation.getResStartTime().getHour(); i < reservation.getResEndTime().getHour(); i++) {
                if (inputTimeMap.get(i) == true) {
                    continue;
                }
                inputTimeMap.replace(i, false, true);
            }
        }

        Iterator<Map.Entry<Integer, Boolean>> itr = inputTimeMap.entrySet().iterator();

        while(itr.hasNext())
        {
            Map.Entry<Integer, Boolean> entry = itr.next();
            if (entry.getValue() == true) {
                resultTimeList.add(entry.getKey());
            }
        }

        // 영업 시간범위에 있는지도 추가
        resultTimeList.sort(Comparator.naturalOrder());
        int minTime = resultTimeList.get(0);
        int maxTime = resultTimeList.get(resultTimeList.size() - 1);
        if (startTime < minTime) {
            for (int i = startTime; i < minTime; i++) {
                resultTimeList.add(i);
            }
        }
        if (endTime > maxTime) {
            for (int i = maxTime + 1; i < endTime; i++) {
                resultTimeList.add(i);
            }
        }
        return resultTimeList;
    }

    public Map<Integer, Boolean> getDefaultTimeMap() {
        Map<Integer, Boolean> timeMap = new HashMap<>();
        for (int i = 0; i < 24; i++) {
            timeMap.put(i, false);
        }
        return timeMap;
    }

    public LocalDate toLocalDate(String year, String month, String day) {
        String StringType = String.format("%s, %s, %s", year, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu, M, d");
        LocalDate resDate = LocalDate.parse(StringType, formatter);
        return resDate;
    }
}