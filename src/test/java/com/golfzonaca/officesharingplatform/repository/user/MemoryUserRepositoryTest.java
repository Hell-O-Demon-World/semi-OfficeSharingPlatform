package com.golfzonaca.officesharingplatform.repository.user;

import com.golfzonaca.officesharingplatform.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class MemoryUserRepositoryTest {
    MemoryUserRepository memoryUserRepository = new MemoryUserRepository();
    String email = "ysh@gmail.com";

    @AfterEach
    private void afterEach() {
        memoryUserRepository.clearStore();
    }
    @Test
    void save() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByEmail() {

    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
        //given

    }

    @Test
    void clearStore() {
    }
}