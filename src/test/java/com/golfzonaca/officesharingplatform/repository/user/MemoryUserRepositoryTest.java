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
        //given
        User user1 = new User("yang", "ysh@gmail.com", "ysh1234", "010-8776-2222", "", "");
        User user2 = new User("yang2", "sh2@gmail.com", "444444", "010-2211-7878", "programmer", "");
        User user3 = new User("yang3", "kkk3@naver.com", "123213", "010-3322-2233", "", "A");

        memoryUserRepository.save(user1);
        memoryUserRepository.save(user2);
        memoryUserRepository.save(user3);
        //when
        User findUser = memoryUserRepository.findByEmail("ysh@gmail.com");
        System.out.println("findUser = " + findUser);
    }

    @Test
    void update() {
    }

    @Test
    void findAll() {
        //given
        User user1 = new User("yang", "ysh@gmail.com", "ysh1234", "010-8776-2222", "", "");
        User user2 = new User("yang2", "sh2@gmail.com", "444444", "010-2211-7878", "programmer", "");
        User user3 = new User("yang3", "kkk3@naver.com", "123213", "010-3322-2233", "", "A");

        memoryUserRepository.save(user1);
        memoryUserRepository.save(user2);
        memoryUserRepository.save(user3);
        //when
        List<User> result = memoryUserRepository.findAll();
        //then
    }

    @Test
    void clearStore() {
    }
}