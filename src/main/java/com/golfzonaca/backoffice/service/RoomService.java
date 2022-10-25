package com.golfzonaca.backoffice.service;

import com.golfzonaca.backoffice.domain.Room;
import com.golfzonaca.backoffice.repository.RoomRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * - 등록 (create)
     */

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    /**
     * - 수정(update)
     * 총 수용인원 수정
     */

    public void updateRoom(Room room, int totalNumber) {
        room.setTotalNumber(totalNumber);
    }

    /**
     *  - 삭제(delete)
     */
    public void removeRoom(Room room) {
        roomRepository.removeRoom(room);
    }

    /**
     * 전체회원 조회
     */

    public List<Room> findRooms() {
        return roomRepository.findAll();
    }
}