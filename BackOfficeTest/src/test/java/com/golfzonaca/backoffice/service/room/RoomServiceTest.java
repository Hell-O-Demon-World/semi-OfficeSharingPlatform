package com.golfzonaca.backoffice.service.room;

import com.golfzonaca.backoffice.domain.Room;
import com.golfzonaca.backoffice.repository.dto.RoomStatusDto;
import com.golfzonaca.backoffice.repository.dto.RoomUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
@Transactional
class RoomServiceTest {

    @Autowired
    RoomService roomService;

    @Test
    void 대여공간저장() {
        //Given
        Room room = new Room(1L, 1L, 1L, 10, false);
        //When
        Room savedRoom = roomService.save(room);
        //Then
        Room findRoom = roomService.findByRoomId(savedRoom.getId()).get();
        assertThat(savedRoom).isEqualTo(findRoom);
    }

    @Test
    void 대여공간업데이트() {
        //Given
        Room room = new Room(1L, 1L, 1L, 10, false);
        Room savedRoom = roomService.save(room);
        long roomId = savedRoom.getId();
        RoomUpdateDto updateParam = new RoomUpdateDto(2L, 20, true);
        //When
        roomService.updateInfo(roomId, updateParam);
        //Then
        Optional<Room> findRoom = roomService.findByRoomId(roomId);
        assertThat(findRoom.get().getRoomKindId()).isEqualTo(2L);
        assertThat(findRoom.get().getTotalNum()).isEqualTo(20);
        assertThat(findRoom.get().getRoomState()).isEqualTo(true);
    }

    @Test
    void 대여공간status갱신() {
        //Given
        Room room = new Room(1L, 1L, 1L, 10, false);
        Room savedRoom = roomService.save(room);
        long roomId = savedRoom.getId();
        RoomStatusDto updateStatus = new RoomStatusDto(true);
        //When
        roomService.updateStatus(roomId, updateStatus);
        //Then
        Optional<Room> findRoom = roomService.findByRoomId(roomId);
        assertThat(findRoom.get().getRoomState()).isEqualTo(true);
    }

    @Test
    void delete() {
        //Given
        Room room = new Room(1L, 1L, 1L, 10, false);
        Room savedRoom = roomService.save(room);
        long placeId = savedRoom.getPlaceId();
        //When
        roomService.deleteByPlaceId(placeId);
        //Then
        Optional<Room> findRoom = roomService.findByRoomId(placeId);
        assertThat(findRoom).isEmpty();
    }

    @Test
    void findByRoomId() {
        //Given
        Room room = new Room(1L, 1L, 1L, 10, false);
        Room savedRoom = roomService.save(room);
        long roomId = savedRoom.getId();
        //When
        Optional<Room> result = roomService.findByRoomId(roomId);
        //Then
        assertThat(result.get()).isEqualTo(savedRoom);
    }

    @Test
    void findAllByPlaceId() {
        //Given
        long companyId = 1L;
        Room roomA = new Room(1L, 1L, companyId, 10, false);
        Room savedRoomA = roomService.save(roomA);
        Room roomB = new Room(2L, 1L, companyId, 20, true);
        Room savedRoomB = roomService.save(roomB);
        //When
        List<Room> result = roomService.findAllByPlaceId(companyId);
        //Then
        assertThat(result).contains(savedRoomA, savedRoomB);
    }
}