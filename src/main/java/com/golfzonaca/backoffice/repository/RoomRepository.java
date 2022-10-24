package com.golfzonaca.backoffice.repository;

import com.golfzonaca.backoffice.domain.Room;

import java.util.List;

public interface RoomRepository {

    Room save(Room room);

    void removeRoom(Room room);

    void updateRoom(Room room);

    List<Room> findAll();
}