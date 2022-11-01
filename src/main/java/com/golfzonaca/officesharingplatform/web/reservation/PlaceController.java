package com.golfzonaca.officesharingplatform.web.reservation;

import com.golfzonaca.officesharingplatform.domain.Place;
import com.golfzonaca.officesharingplatform.domain.Room;
import com.golfzonaca.officesharingplatform.repository.PlaceRepository;
import com.golfzonaca.officesharingplatform.repository.room.RoomKindRepository;
import com.golfzonaca.officesharingplatform.repository.room.RoomRepository;
import com.golfzonaca.officesharingplatform.web.reservation.form.ReservationViewFrom;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceRepository placeRepository;
    private final RoomRepository roomRepository;
//    private final RoomKindRepository roomKindRepository;

//    @GetMapping("/{placeId}")
//    public ReservationViewFrom reservationView(@PathVariable long placeId) {
//        ReservationViewFrom reservationViewFrom = new ReservationViewFrom();
//        Map<Long, Room> roomMap = roomRepository.findRoomsByPlaceId(placeId);
//        Iterator<Long> keys = roomMap.keySet().iterator();
//        while( keys.hasNext() ){
//            Long key = keys.next();
//            Room storedRoom = roomMap.get(key);
//            if (storedRoom.getRoomKindId() == (placeId)){
//                store.put(key, storedRoom);
//            }
//        }
//        reservationViewFrom.setDesk();
//        return reservationViewFrom;
//    }
    @PostMapping("/{placeId}")
    public String reservationForm(@PathVariable long placeId, Model model) {
        Place place = placeRepository.findById(placeId);
        model.addAttribute("place", place);
        return ("");
    }
}
