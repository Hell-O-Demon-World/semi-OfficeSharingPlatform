package com.golfzonaca.officesharingplatform.config.auth;

import com.golfzonaca.officesharingplatform.config.auth.repository.User2Repository;
import com.golfzonaca.officesharingplatform.config.auth.token.User2;
import com.golfzonaca.officesharingplatform.domain.User;
import com.golfzonaca.officesharingplatform.repository.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class PrincipalDetailsRepository {
    private static final ConcurrentHashMap<Long, PrincipalDetails> store = new ConcurrentHashMap<>();

    public PrincipalDetails save(Long id, PrincipalDetails principalDetails) {
        store.put(id, principalDetails);
        return principalDetails;
    }

    public PrincipalDetails findById(long id) {
        return store.get(id);
    }

    public PrincipalDetails findByEmail(String email) {
        Iterator<Long> keys = store.keySet().iterator();

        while (keys.hasNext()) {
            Long key = keys.next();
            PrincipalDetails principalDetails = store.get(key);
            System.out.println("Iter : principalDetails = " + principalDetails);
            System.out.println("email = " + email);
            if (principalDetails.getUsername().equals(email)) {
                return principalDetails;
            }
        }
        return null;
    }

    public List<PrincipalDetails> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
