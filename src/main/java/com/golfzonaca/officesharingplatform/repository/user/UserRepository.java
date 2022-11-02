package com.golfzonaca.officesharingplatform.repository.user;

import com.golfzonaca.officesharingplatform.domain.User;

import java.util.List;

public interface UserRepository {
    User save(User user);

    User findById(long id);

    User findByEmail(String email);

    int countContainByEmail(String email);
    User update(long id, User user);

    List<User> findAll();

    void clearStore();
}
