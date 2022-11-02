package com.golfzonaca.officesharingplatform.repository.user;

import com.golfzonaca.officesharingplatform.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MyBatisUserRepository implements UserRepository {
    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User findById(long id) {
        return null;
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public int countContainByEmail(String email) {
        return 0;
    }

    @Override
    public User update(long id, User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void clearStore() {

    }
}
