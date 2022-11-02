package com.golfzonaca.officesharingplatform.repository.user;

import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.repository.mybatis.dto.UserInfoDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryUserRepository implements UserRepository {

    private static final ConcurrentHashMap<Long, User> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public User save(User user) {

        user.setId(sequence++);
        store.put(user.getId(), user);

        return user;
    }

    @Override
    public User findById(long id) {
        return store.get(id);
    }

    @Override
    public User findByEmail(String email) {

        User findUser = new User("", "", "", "", "", "");
        Iterator<Long> keys = store.keySet().iterator();
        while (keys.hasNext()) {
            Long key = keys.next();
            User storedUser = store.get(key);
            if (storedUser.getUserMail().equals(email)) {
                return storedUser;
            }
        }

        return findUser;
    }

    public int countContainByEmail(String email) {

        Iterator<Long> keys = store.keySet().iterator();
        int cnt = 0;
        while (keys.hasNext()) {
            Long key = keys.next();
            User storedUser = store.get(key);
            if (storedUser.getUserMail().equals(email)) {
                cnt++;
            }
        }

        return cnt;
    }

    @Override
    public User update(long id, UserInfoDto updateParam) {

        User findUser = findById(id);

        findUser.setUserTel(updateParam.getUserTel());
        findUser.setUserJob(updateParam.getUserJob());
        findUser.setUserPlace(updateParam.getPreferType().toString());

        return findUser;
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

}
