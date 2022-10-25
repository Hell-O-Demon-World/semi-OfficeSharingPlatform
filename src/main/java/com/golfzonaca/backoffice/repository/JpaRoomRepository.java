package com.golfzonaca.backoffice.repository;

import com.golfzonaca.backoffice.domain.Room;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class JpaRoomRepository implements RoomRepository {
    private final EntityManager entityManager;

    public JpaRoomRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Room save(Room room) {
        entityManager.persist(room);
        return room;
    }

    @Override
    public void removeRoom(Room room) {
        entityManager.remove(room);
    }

    @Override
    public void updateRoom(Room room) {
        entityManager.refresh(room);
    }

    @Override
    public List<Room> findAll() {
        return entityManager.createQuery("select r from Room r", Room.class).getResultList();
    }
}
