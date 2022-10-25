package com.golfzonaca.officesharingplatform.repository.user;

import com.golfzonaca.officesharingplatform.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryUserRepositoryTest {
    MemoryUserRepository memoryUserRepository = new MemoryUserRepository();

    @AfterEach
    public void afterEach() {
        memoryUserRepository.clearStore();
    }

    @Test
    void save() {
        // given
        User user1 = new User("yang", "yang1234@gmail.com", "yang1234!",2L);
        User user2 = new User("yang2", "yang2@gmail.com", "yang2!", 4L);

        // when
        User saveUser1 = memoryUserRepository.save(user1);
        User saveUser2 = memoryUserRepository.save(user2);

        // then
        System.out.println("user1.getId() = " + user1.getId());
        System.out.println("user2 = " + user2.getId());
        User findUser1 = memoryUserRepository.findById(user1.getId());
        User findUser2 = memoryUserRepository.findById(user2.getId());
        assertThat(findUser1).isEqualTo(saveUser1);
        assertThat(findUser2).isEqualTo(saveUser2);
    }

    @Test
    void findById() {
        //given
        User user1 = new User( "yang", "yang1234@gmail.com", "yang1234!", 2L);
        //when
        memoryUserRepository.save(user1);
        User findUser = memoryUserRepository.findById(user1.getId());
        //then
        assertThat(findUser.getId()).isEqualTo(user1.getId());
    }

    @Test
    void update() {
        //given
        User user1 = new User("yang", "yang1234@gmail.com", "yang1234!", 2L);

        //when
        memoryUserRepository.save(user1);
        User updateParam = new User("yang2", "yang22", "yang2", 2L);
        memoryUserRepository.update(user1.getId(), updateParam);
        //then
        assertThat(updateParam.getJob()).isSameAs(user1.getJob());
    }

    @Test
    void findAll() {
        User user1 = new User("yang", "yang1234@gmail.com", "yang1234!", 2L);
        User user2 = new User("yang2", "yang2@gmail.com", "yang2!", 4L);

        memoryUserRepository.save(user1);
        memoryUserRepository.save(user2);

        List<User> users = memoryUserRepository.findAll();

        assertThat(users.size()).isEqualTo(2);
        assertThat(users).contains(user1, user2);

    }

    @Test
    void clearStore() {
        User user1 = new User("yang", "yang1234@gmail.com", "yang1234!", 2L);
        User user2 = new User("yang2", "yang2@gmail.com", "yang2!", 4L);

        memoryUserRepository.save(user1);
        memoryUserRepository.save(user2);
        List<User> beforeUsers = memoryUserRepository.findAll();
        memoryUserRepository.clearStore();
        List<User> afterUsers = memoryUserRepository.findAll();

        assertThat(beforeUsers.size()).isEqualTo(2);
        assertThat(afterUsers.size()).isEqualTo(0);

    }
}