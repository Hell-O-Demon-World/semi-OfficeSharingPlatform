package com.golfzonaca.officesharingplatform.config.auth.repository;

import com.golfzonaca.officesharingplatform.config.auth.token.User2;
import com.golfzonaca.officesharingplatform.repository.user.MemoryUserRepository;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class User2Repository {
    private static final ConcurrentHashMap<Long, User2> store = new ConcurrentHashMap<>();

    public User2 save(Long id, User2 user2) {
        user2.setId(id);
        store.put(id, user2);
        return user2;
    }

    public User2 findById(long id) {
        return store.get(id);
    }

    public User2 findByUsername(String username) {
        Iterator<Long> keys = store.keySet().iterator();

        while( keys.hasNext() ){
            Long key = keys.next();
            User2 user2 = store.get(key);
            if (user2.getUsername().equals(username)){
                return user2;
            }
        }
        return null;
    }

    public List<User2> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
