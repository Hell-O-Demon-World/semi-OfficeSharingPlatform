package com.golfzonaca.backoffice.service.room;

import com.golfzonaca.backoffice.domain.Room;
import com.golfzonaca.backoffice.repository.RoomRepository;
import com.golfzonaca.backoffice.repository.dto.RoomStatusDto;
import com.golfzonaca.backoffice.repository.dto.RoomUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyBatisRoomService implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void updateInfo(long id, RoomUpdateDto updateParam) {
        roomRepository.updateInfo(id, updateParam);
    }

    @Override
    public void updateStatus(long id, RoomStatusDto updateStatus) {
        roomRepository.updateStatus(id, updateStatus);
    }

    @Override
    public void deleteByPlaceId(long placeId) {
        roomRepository.deleteByPlaceId(placeId);
    }

    @Override
    public Optional<Room> findByRoomId(long id) {
        return roomRepository.findByRoomId(id);
    }

    @Override
    public List<Room> findAllByPlaceId(long id) {
        return roomRepository.findAllByPlaceId(id);
    }

    @Override
    public List<Integer> countByRoomType(long placeId) {
        return roomRepository.countByRoomType(placeId);
    }
}
